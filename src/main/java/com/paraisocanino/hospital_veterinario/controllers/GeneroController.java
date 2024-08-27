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

import com.paraisocanino.hospital_veterinario.models.Genero;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.GeneroRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/genero")
public class GeneroController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllGeneros(@RequestHeader("Authorization") String tokenAdmin) {
        List<Genero> generos = generoRepository.findAll();
        List<Genero> generoResponse = new ArrayList<>();

        for (Genero genero : generos) {
            final Genero model = new Genero();
            model.setIdGenero(genero.getIdGenero());
            model.setNombre(genero.getNombre());
            model.setUsuariocreacion(genero.getUsuariocreacion());
            model.setFechacreacion(genero.getFechacreacion());
            model.setUsuariomodificacion(genero.getUsuariomodificacion());
            model.setFechamodificacion(genero.getFechamodificacion());

            generoResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(generoResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createGenero(@RequestBody Genero genero, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        genero.setFechamodificacion(dateNow());
        genero.setUsuariomodificacion(user);
        genero.setUsuariocreacion(user);
        genero.setFechacreacion(dateNow());

        generoRepository.save(genero);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(genero);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateGenero(@RequestBody Genero genero, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Genero> currentStatusEmpleado = generoRepository.findById(genero.getIdGenero());

        if (currentStatusEmpleado.isPresent()) {

            genero.setFechamodificacion(dateNow());
            genero.setUsuariomodificacion(user);
            genero.setUsuariocreacion(currentStatusEmpleado.get().getUsuariocreacion());
            genero.setFechacreacion(currentStatusEmpleado.get().getFechacreacion());
            generoRepository.save(genero);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(genero);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteGenero(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Genero> currentGenero= generoRepository.findById(id);

        if (currentGenero.isPresent()) {
            generoRepository.delete(currentGenero.get());
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
