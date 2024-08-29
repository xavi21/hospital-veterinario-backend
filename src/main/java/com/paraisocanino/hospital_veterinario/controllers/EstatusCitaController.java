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

import com.paraisocanino.hospital_veterinario.models.EstatusCita;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.EstatusCitaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/estatus-cita")
public class EstatusCitaController {
@Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EstatusCitaRepository estatusCitaRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllEstadosCiviles(@RequestHeader("Authorization") String tokenAdmin) {
        List<EstatusCita> estatusesCita = estatusCitaRepository.findAll();
        List<EstatusCita> estatusCitaResponse = new ArrayList<>();

        for (EstatusCita estatusCita : estatusesCita) {
            final EstatusCita model = new EstatusCita();
            model.setIdestatuscita(estatusCita.getIdestatuscita());
            model.setNombre(estatusCita.getNombre());
            model.setUsuariocreacion(estatusCita.getUsuariocreacion());
            model.setFechacreacion(estatusCita.getFechacreacion());
            model.setUsuariomodificacion(estatusCita.getUsuariomodificacion());
            model.setFechamodificacion(estatusCita.getFechamodificacion());

            estatusCitaResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(estatusCitaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createTalla(@RequestBody EstatusCita estatusCita, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        estatusCita.setFechamodificacion(dateNow());
        estatusCita.setUsuariomodificacion(user);
        estatusCita.setUsuariocreacion(user);
        estatusCita.setFechacreacion(dateNow());

        estatusCitaRepository.save(estatusCita);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(estatusCita);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateTalla(@RequestBody EstatusCita estatusCita, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<EstatusCita> currentStatusEmpleado = estatusCitaRepository.findById(estatusCita.getIdestatuscita());

        if (currentStatusEmpleado.isPresent()) {

            estatusCita.setFechamodificacion(dateNow());
            estatusCita.setUsuariomodificacion(user);
            estatusCita.setUsuariocreacion(currentStatusEmpleado.get().getUsuariocreacion());
            estatusCita.setFechacreacion(currentStatusEmpleado.get().getFechacreacion());
            estatusCitaRepository.save(estatusCita);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(estatusCita);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteTalla(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<EstatusCita> currentEstatusCita= estatusCitaRepository.findById(id);

        if (currentEstatusCita.isPresent()) {
            estatusCitaRepository.delete(currentEstatusCita.get());
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
