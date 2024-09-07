package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Mascota;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.MascotaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        List<Mascota> mascotas = mascotaRepository.findAll();

        List<Mascota> mascotaResponse = new ArrayList<>();

        for (Mascota mascota : mascotas) {
            Mascota mascotar = new Mascota();
            mascotar.setIdmascota(mascota.getIdmascota());
            mascotar.setIdTipoMascota(mascota.getIdTipoMascota());
            mascotar.setId_genero(mascota.getId_genero());
            mascotar.setIdpersona(mascota.getIdpersona());
            mascotar.setIdColor(mascota.getIdColor());
            mascotar.setIdTalla(mascota.getIdTalla());
            mascotar.setNombre(mascota.getNombre());
            mascotar.setPeso(mascota.getPeso());
            mascotar.setFechacreacion(mascota.getFechacreacion());
            mascotar.setUsuariocreacion(mascota.getUsuariocreacion());
            mascotar.setFechamodificacion(mascota.getFechamodificacion());
            mascotar.setUsuariomodificacion(mascota.getUsuariomodificacion());
            mascotaResponse.add(mascotar);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(mascotaResponse);

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

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
