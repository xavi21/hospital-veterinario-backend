package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.models.Receta;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.*;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/receta")
public class RecetaController {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getPersonas(@RequestHeader("Authorization") String tokenAdmin) {
        List<RecetaProjection> receta = recetaRepository.findAllReceta();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(receta);

        return response;
    }

    @GetMapping("/medicamentoByConsulta")
    public GeneralResponseList getMedicamentoByConsulta(@RequestHeader("Authorization") String tokenAdmin, @RequestParam Integer idconsulta) {
        List<RecetaProjection> consultaMedicamento = recetaRepository.findmedicamentoByConsulta(idconsulta);


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(consultaMedicamento);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postReceta(@RequestBody Receta receta, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        receta.setFechamodificacion(dateNow());
        receta.setUsuariomodificacion(user);
        receta.setUsuariocreacion(user);
        receta.setFechacreacion(dateNow());

        recetaRepository.save(receta);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(receta);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateReceta(@RequestBody Receta receta, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Receta> currentPersona = recetaRepository.findById(receta.getIdreceta());

        if (currentPersona.isPresent()) {

            receta.setFechamodificacion(dateNow());
            receta.setUsuariomodificacion(user);
            receta.setUsuariocreacion(currentPersona.get().getUsuariocreacion());
            receta.setFechacreacion(currentPersona.get().getFechacreacion());
            recetaRepository.save(receta);

            response.setCode(200);
            response.setMessage("Receta Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(receta);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteReceta(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Receta> currentReceta = recetaRepository.findById(id);

        if (currentReceta.isPresent()) {
            recetaRepository.delete(currentReceta.get());
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
