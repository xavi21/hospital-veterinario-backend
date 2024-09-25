package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Grooming;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.GroomingProjection;
import com.paraisocanino.hospital_veterinario.repository.GroomingRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/grooming")
public class GroomingController {

    @Autowired
    private GroomingRepository groomingRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getPersonas(@RequestHeader("Authorization") String tokenAdmin) {
        List<GroomingProjection> groomings = groomingRepository.findAllGrooming();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(groomings);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postGrooming(@RequestBody Grooming grooming, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        grooming.setFechamodificacion(dateNow());
        grooming.setUsuariomodificacion(user);
        grooming.setUsuariocreacion(user);
        grooming.setFechacreacion(dateNow());

        groomingRepository.save(grooming);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(grooming);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateGrooming(@RequestBody Grooming grooming, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Grooming> currentGrooming = groomingRepository.findById(grooming.getIdgrooming());

        if (currentGrooming.isPresent()) {

            grooming.setFechamodificacion(dateNow());
            grooming.setUsuariomodificacion(user);
            grooming.setUsuariocreacion(currentGrooming.get().getUsuariocreacion());
            grooming.setFechacreacion(currentGrooming.get().getFechacreacion());
            groomingRepository.save(grooming);

            response.setCode(200);
            response.setMessage("Grooming Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(grooming);
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}

