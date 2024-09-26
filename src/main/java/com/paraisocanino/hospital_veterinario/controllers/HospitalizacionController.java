package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Hospitalizacion;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionProjection;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hospitalizacion")
public class HospitalizacionController {

    @Autowired
    private HospitalizacionRepository hospitalizacionRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getPersonas(@RequestHeader("Authorization") String tokenAdmin) {
        List<HospitalizacionProjection> hospitalizacion = hospitalizacionRepository.findAllHospitalizacion();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(hospitalizacion);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postHospitalizacion(@RequestBody Hospitalizacion hospitalizacion, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        hospitalizacion.setFechamodificacion(dateNow());
        hospitalizacion.setUsuariomodificacion(user);
        hospitalizacion.setUsuariocreacion(user);
        hospitalizacion.setFechacreacion(dateNow());

        hospitalizacionRepository.save(hospitalizacion);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(hospitalizacion);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updatePersona(@RequestBody Hospitalizacion hospitalizacion, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Hospitalizacion> currentHospitalizacion = hospitalizacionRepository.findById(hospitalizacion.getIdhospitalizacion());

        if (currentHospitalizacion.isPresent()) {

            hospitalizacion.setFechamodificacion(dateNow());
            hospitalizacion.setUsuariomodificacion(user);
            hospitalizacion.setUsuariocreacion(currentHospitalizacion.get().getUsuariocreacion());
            hospitalizacion.setFechacreacion(currentHospitalizacion.get().getFechacreacion());
            hospitalizacionRepository.save(hospitalizacion);

            response.setCode(200);
            response.setMessage("Persona Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(hospitalizacion);
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
