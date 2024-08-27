package com.paraisocanino.hospital_veterinario.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paraisocanino.hospital_veterinario.models.Ambulancia;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.AmbulanciaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ambulancia")
public class AmbulanciaController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AmbulanciaRepository ambulanciaRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllAmbulancias(@RequestHeader("Authorization") String tokenAdmin) {

        List<Ambulancia> ambulancias = ambulanciaRepository.findAll();

        List<Ambulancia> ambulanciaResponse = new ArrayList<>();

        for (Ambulancia ambulancia : ambulancias) {
            final Ambulancia model = new Ambulancia();
            model.setIdAmbulancia(ambulancia.getIdAmbulancia());
            model.setPlaca(ambulancia.getPlaca());
            model.setMarca(ambulancia.getMarca());
            model.setModelo(ambulancia.getModelo());
            model.setLatitud(ambulancia.getLatitud());
            model.setLongitud(ambulancia.getLongitud());
            model.setIdEmpleado(ambulancia.getIdEmpleado());
            model.setUsuariocreacion(ambulancia.getUsuariocreacion());
            model.setFechacreacion(ambulancia.getFechacreacion());
            model.setUsuariomodificacion(ambulancia.getUsuariomodificacion());
            model.setFechamodificacion(ambulancia.getFechamodificacion());

            ambulanciaResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(ambulanciaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createAmbulancia(@RequestBody Ambulancia ambulancia, @RequestHeader("Authorization") String tokenAdmin) {
        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        ambulancia.setFechamodificacion(dateNow());
        ambulancia.setUsuariomodificacion(user);
        ambulancia.setUsuariocreacion(user);
        ambulancia.setFechacreacion(dateNow());

        ambulanciaRepository.save(ambulancia);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(ambulancia);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateAmbulancia(@RequestBody Ambulancia ambulancia, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();
Optional<Ambulancia> currentAmbulancia = ambulanciaRepository.findById(ambulancia.getIdAmbulancia());

        if (currentAmbulancia.isPresent()) {
            ambulancia.setFechamodificacion(dateNow());
            ambulancia.setUsuariomodificacion(user);
            ambulancia.setUsuariocreacion(currentAmbulancia.get().getUsuariocreacion());
            ambulancia.setFechacreacion(currentAmbulancia.get().getFechacreacion());
            ambulanciaRepository.save(ambulancia);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(ambulancia);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteAmbulancia(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Ambulancia> currentAmbulancia = ambulanciaRepository.findById(id);

        if (currentAmbulancia.isPresent()) {
            ambulanciaRepository.delete(currentAmbulancia.get());
            response.setCode(200);
            response.setMessage("Registro Eliminado: " + id);

        } else {
            response.setCode(200);
            response.setMessage("No existe registro :" + id);
        }
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
