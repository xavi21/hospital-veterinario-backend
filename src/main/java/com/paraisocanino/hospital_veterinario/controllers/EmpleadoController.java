package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Empleado;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.EmpleadoRepository;
import com.paraisocanino.hospital_veterinario.repository.PersonaProjection;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private JwtUtils jwtUtils;

//    @GetMapping("/all")
//    public GeneralResponseList getEmpleado(@RequestHeader("Authorization") String tokenAdmin) {
//        List<PersonaProjection> personas = personaRepository.findAllPersona();
//
//        final GeneralResponseList response = new GeneralResponseList();
//        response.setCode(200);
//        response.setMessage("Exitoso");
//        response.setData(personas);
//
//        return response;
//    }

    @PostMapping("/save")
    public GeneralResponse postPersona(@RequestBody Empleado empleado, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        empleado.setFechamodificacion(dateNow());
        empleado.setUsuariomodificacion(user);
        empleado.setUsuariocreacion(user);
        empleado.setFechacreacion(dateNow());

        empleadoRepository.save(empleado);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(empleado);

        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
