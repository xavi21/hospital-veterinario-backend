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

import com.paraisocanino.hospital_veterinario.models.Jaula;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.JaulaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jaula")
public class JaulaController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JaulaRepository jaulaRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllJaulas(@RequestHeader("Authorization") String tokenAdmin) {
        List<Jaula> jaulas = jaulaRepository.findAll();

        List<Jaula> jaulaResponse = new ArrayList<>();

        for (Jaula jaula : jaulas) {
            final Jaula model = new Jaula();
            model.setIdJaula(jaula.getIdJaula());
            model.setDescripcion(jaula.getDescripcion());
            model.setUsuariocreacion(jaula.getUsuariocreacion());
            model.setFechacreacion(jaula.getFechacreacion());
            model.setUsuariomodificacion(jaula.getUsuariomodificacion());
            model.setFechamodificacion(jaula.getFechamodificacion());

            jaulaResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(jaulaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createJaula(@RequestBody Jaula jaula, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        jaula.setFechamodificacion(dateNow());
        jaula.setUsuariomodificacion(user);
        jaula.setUsuariocreacion(user);
        jaula.setFechacreacion(dateNow());

        jaulaRepository.save(jaula);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(jaula);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateJaula(@RequestBody Jaula jaula, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Jaula> currentJaula = jaulaRepository.findById(jaula.getIdJaula());

        if (currentJaula.isPresent()) {

            jaula.setFechamodificacion(dateNow());
            jaula.setUsuariomodificacion(user);
            jaula.setUsuariocreacion(currentJaula.get().getUsuariocreacion());
            jaula.setFechacreacion(currentJaula.get().getFechacreacion());
            jaulaRepository.save(jaula);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(jaula);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteJaula(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Jaula> currentJaula = jaulaRepository.findById(id);

        if (currentJaula.isPresent()) {
            jaulaRepository.delete(currentJaula.get());
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
