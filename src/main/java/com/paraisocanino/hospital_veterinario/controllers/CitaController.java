package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Cita;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.CitaProjection;
import com.paraisocanino.hospital_veterinario.repository.CitaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cita")
public class CitaController {
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getCita(@RequestHeader("Authorization") String tokenAdmin) {
        List<CitaProjection> citas = citaRepository.findAllCita();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(citas);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postCita(@RequestBody Cita cita, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        cita.setFechamodificacion(dateNow());
        cita.setUsuariomodificacion(user);
        cita.setUsuariocreacion(user);
        cita.setFechacreacion(dateNow());

        citaRepository.save(cita);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(cita);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateCita(@RequestBody Cita cita, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Cita> currentCita = citaRepository.findById(cita.getIdcita());

        if (currentCita.isPresent()) {

            cita.setFechamodificacion(dateNow());
            cita.setUsuariomodificacion(user);
            cita.setUsuariocreacion(currentCita.get().getUsuariocreacion());
            cita.setFechacreacion(currentCita.get().getFechacreacion());
            citaRepository.save(cita);

            response.setCode(200);
            response.setMessage("Empleado Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(cita);
        return response;
    }

    @PutMapping("/eliminar")
    public GeneralResponse deleteCita(@RequestBody Cita cita, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Cita> currentCita = citaRepository.findById(cita.getIdcita());

        if (currentCita.isPresent()) {

            cita.setFechamodificacion(dateNow());
            cita.setUsuariomodificacion(user);
            cita.setUsuariocreacion(currentCita.get().getUsuariocreacion());
            cita.setFechacreacion(currentCita.get().getFechacreacion());
            cita.setIdstatuscita(2);
            citaRepository.save(cita);

            response.setCode(200);
            response.setMessage("Cita Cancelada");
        } else {
            response.setCode(401);
            response.setMessage("Error al Cancelar");
        }

        response.setData(cita);
        return response;
    }


    private LocalDate dateNow() {
        return LocalDate.now();
    }

}
