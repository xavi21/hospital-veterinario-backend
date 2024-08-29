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

import com.paraisocanino.hospital_veterinario.models.Puesto;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.PuestoRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/puesto")
public class PuestoController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PuestoRepository puestoRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllPuestos(@RequestHeader("Authorization") String tokenAdmin) {
        List<Puesto> puestos = puestoRepository.findAll();
        List<Puesto> puestoResponse = new ArrayList<>();

        for (Puesto puesto : puestos) {
            final Puesto model = new Puesto();
            model.setIdpuesto(puesto.getIdpuesto());
            model.setNombre(puesto.getNombre());
            model.setUsuariocreacion(puesto.getUsuariocreacion());
            model.setFechacreacion(puesto.getFechacreacion());
            model.setUsuariomodificacion(puesto.getUsuariomodificacion());
            model.setFechamodificacion(puesto.getFechamodificacion());

            puestoResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(puestoResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createPuesto(@RequestBody Puesto puesto, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        puesto.setFechamodificacion(dateNow());
        puesto.setUsuariomodificacion(user);
        puesto.setUsuariocreacion(user);
        puesto.setFechacreacion(dateNow());

        puestoRepository.save(puesto);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(puesto);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updatePuesto(@RequestBody Puesto puesto, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Puesto> currentPuesto = puestoRepository.findById(puesto.getIdpuesto());

        if (currentPuesto.isPresent()) {
            puesto.setFechamodificacion(dateNow());
            puesto.setUsuariomodificacion(user);
            puesto.setUsuariocreacion(currentPuesto.get().getUsuariocreacion());
            puesto.setFechacreacion(currentPuesto.get().getFechacreacion());
            puestoRepository.save(puesto);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(puesto);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deletePuesto(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Puesto> currentPuesto = puestoRepository.findById(id);

        if (currentPuesto.isPresent()) {
            puestoRepository.delete(currentPuesto.get());
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
