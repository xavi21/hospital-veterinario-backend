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

import com.paraisocanino.hospital_veterinario.models.EstadoCivil;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.EstadoCivilRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/estado-civil")
public class EstadoCivilController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllEstadosCiviles(@RequestHeader("Authorization") String tokenAdmin) {
        List<EstadoCivil> estadosCiviles = estadoCivilRepository.findAll();
        List<EstadoCivil> estadoCivilResponse = new ArrayList<>();

        for (EstadoCivil estadoCivil : estadosCiviles) {
            final EstadoCivil model = new EstadoCivil();
            model.setId_estado_civil(estadoCivil.getId_estado_civil());
            model.setNombre(estadoCivil.getNombre());
            model.setUsuariocreacion(estadoCivil.getUsuariocreacion());
            model.setFechacreacion(estadoCivil.getFechacreacion());
            model.setUsuariomodificacion(estadoCivil.getUsuariomodificacion());
            model.setFechamodificacion(estadoCivil.getFechamodificacion());

            estadoCivilResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(estadoCivilResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createTalla(@RequestBody EstadoCivil estadoCivil, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        estadoCivil.setFechamodificacion(dateNow());
        estadoCivil.setUsuariomodificacion(user);
        estadoCivil.setUsuariocreacion(user);
        estadoCivil.setFechacreacion(dateNow());

        estadoCivilRepository.save(estadoCivil);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(estadoCivil);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateTalla(@RequestBody EstadoCivil estadoCivil, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<EstadoCivil> currentStatusEmpleado = estadoCivilRepository.findById(estadoCivil.getId_estado_civil());

        if (currentStatusEmpleado.isPresent()) {

            estadoCivil.setFechamodificacion(dateNow());
            estadoCivil.setUsuariomodificacion(user);
            estadoCivil.setUsuariocreacion(currentStatusEmpleado.get().getUsuariocreacion());
            estadoCivil.setFechacreacion(currentStatusEmpleado.get().getFechacreacion());
            estadoCivilRepository.save(estadoCivil);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(estadoCivil);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteTalla(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<EstadoCivil> currentEstadoCivil= estadoCivilRepository.findById(id);

        if (currentEstadoCivil.isPresent()) {
            estadoCivilRepository.delete(currentEstadoCivil.get());
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
