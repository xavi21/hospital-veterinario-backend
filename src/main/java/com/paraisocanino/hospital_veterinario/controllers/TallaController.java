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

import com.paraisocanino.hospital_veterinario.models.Talla;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.TallaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/talla")
public class TallaController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TallaRepository tallaRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllTallas(@RequestHeader("Authorization") String tokenAdmin) {
        List<Talla> tallas = tallaRepository.findAll();
        List<Talla> tallaResponse = new ArrayList<>();

        for (Talla talla : tallas) {
            final Talla model = new Talla();
            model.setIdTalla(talla.getIdTalla());
            model.setNombre(talla.getNombre());
            model.setUsuariocreacion(talla.getUsuariocreacion());
            model.setFechacreacion(talla.getFechacreacion());
            model.setUsuariomodificacion(talla.getUsuariomodificacion());
            model.setFechamodificacion(talla.getFechamodificacion());

            tallaResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(tallaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createTalla(@RequestBody Talla talla, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        talla.setFechamodificacion(dateNow());
        talla.setUsuariomodificacion(user);
        talla.setUsuariocreacion(user);
        talla.setFechacreacion(dateNow());

        tallaRepository.save(talla);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(talla);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateTalla(@RequestBody Talla talla, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Talla> currentStatusEmpleado = tallaRepository.findById(talla.getIdTalla());

        if (currentStatusEmpleado.isPresent()) {

            talla.setFechamodificacion(dateNow());
            talla.setUsuariomodificacion(user);
            talla.setUsuariocreacion(currentStatusEmpleado.get().getUsuariocreacion());
            talla.setFechacreacion(currentStatusEmpleado.get().getFechacreacion());
            tallaRepository.save(talla);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(talla);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteTalla(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Talla> currentStatusEmpleado = tallaRepository.findById(id);

        if (currentStatusEmpleado.isPresent()) {
            tallaRepository.delete(currentStatusEmpleado.get());
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
