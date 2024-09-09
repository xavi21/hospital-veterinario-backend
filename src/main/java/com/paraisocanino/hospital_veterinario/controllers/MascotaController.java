package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Mascota;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.MascotaProjection;
import com.paraisocanino.hospital_veterinario.repository.MascotaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mascota")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getMascota(@RequestHeader("Authorization") String tokenAdmin) {
        List<MascotaProjection> mascotas = mascotaRepository.findAllMascota();


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(mascotas);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postMascota(@RequestBody Mascota mascota, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        mascota.setFechamodificacion(dateNow());
        mascota.setUsuariomodificacion(user);
        mascota.setUsuariocreacion(user);
        mascota.setFechacreacion(dateNow());

        mascotaRepository.save(mascota);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(mascota);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateMascota(@RequestBody Mascota mascota, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Mascota> currentMascota = mascotaRepository.findById(mascota.getIdmascota());

        if (currentMascota.isPresent()) {

            mascota.setFechamodificacion(dateNow());
            mascota.setUsuariomodificacion(user);
            mascota.setUsuariocreacion(currentMascota.get().getUsuariocreacion());
            mascota.setFechacreacion(currentMascota.get().getFechacreacion());
            mascotaRepository.save(mascota);

            response.setCode(200);
            response.setMessage("Persona Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(mascota);
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
