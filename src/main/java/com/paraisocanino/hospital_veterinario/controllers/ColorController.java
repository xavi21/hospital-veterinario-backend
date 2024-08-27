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

import com.paraisocanino.hospital_veterinario.models.Color;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.ColorRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/color")
public class ColorController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ColorRepository colorRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllColores(@RequestHeader("Authorization") String tokenAdmin) {
        List<Color> colores = colorRepository.findAll();
        List<Color> colorResponse = new ArrayList<>();

        for (Color color : colores) {
            final Color model = new Color();
            model.setIColor(color.getIdColor());
            model.setNombre(color.getNombre());
            model.setUsuariocreacion(color.getUsuariocreacion());
            model.setFechacreacion(color.getFechacreacion());
            model.setUsuariomodificacion(color.getUsuariomodificacion());
            model.setFechamodificacion(color.getFechamodificacion());

            colorResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(colorResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createColor(@RequestBody Color color, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        color.setFechamodificacion(dateNow());
        color.setUsuariomodificacion(user);
        color.setUsuariocreacion(user);
        color.setFechacreacion(dateNow());

        colorRepository.save(color);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(color);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateColor(@RequestBody Color color, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Color> currentColor = colorRepository.findById(color.getIdColor());

        if (currentColor.isPresent()) {
            color.setFechamodificacion(dateNow());
            color.setUsuariomodificacion(user);
            color.setUsuariocreacion(currentColor.get().getUsuariocreacion());
            color.setFechacreacion(currentColor.get().getFechacreacion());
            colorRepository.save(color);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(color);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteColor(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Color> currentColor = colorRepository.findById(id);

        if (currentColor.isPresent()) {
            colorRepository.delete(currentColor.get());
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
