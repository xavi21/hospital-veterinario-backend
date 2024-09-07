package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.models.Talla;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.PersonaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getPersonas(@RequestHeader("Authorization") String tokenAdmin) {
        List<Persona> personas = personaRepository.findAll();

        List<Persona> personaResponse = new ArrayList<>();

        for (Persona persona : personas) {
            Persona personar = new Persona();
            personar.setIdpersona(persona.getIdpersona());
            personar.setNombre(persona.getNombre());
            personar.setApellido(persona.getApellido());
            personar.setFechanacimiento(persona.getFechanacimiento());
            personar.setId_genero(persona.getId_genero());
            personar.setDireccion(persona.getDireccion());
            personar.setTelefono(persona.getTelefono());
            personar.setCorreoelectronico(persona.getCorreoelectronico());
            personar.setId_estado_civil(persona.getId_estado_civil());
            personar.setFechacreacion(persona.getFechacreacion());
            personar.setUsuariocreacion(persona.getUsuariocreacion());
            personar.setFechamodificacion(persona.getFechamodificacion());
            personar.setUsuariomodificacion(persona.getUsuariomodificacion());

            personaResponse.add(personar);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(personaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postPersona(@RequestBody Persona persona, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        persona.setFechamodificacion(dateNow());
        persona.setUsuariomodificacion(user);
        persona.setUsuariocreacion(user);
        persona.setFechacreacion(dateNow());

        personaRepository.save(persona);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(persona);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updatePersona(@RequestBody Persona persona, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Persona> currentPersona = personaRepository.findById(persona.getIdpersona());

        if (currentPersona.isPresent()) {

            persona.setFechamodificacion(dateNow());
            persona.setUsuariomodificacion(user);
            persona.setUsuariocreacion(currentPersona.get().getUsuariocreacion());
            persona.setFechacreacion(currentPersona.get().getFechacreacion());
            personaRepository.save(persona);

            response.setCode(200);
            response.setMessage("Persona Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(persona);
        return response;
    }


    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
