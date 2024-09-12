package com.paraisocanino.hospital_veterinario.controllers;


import com.paraisocanino.hospital_veterinario.models.Cita;
import com.paraisocanino.hospital_veterinario.models.Consulta;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.ConsultaProjection;
import com.paraisocanino.hospital_veterinario.repository.ConsultaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getConsulta(@RequestHeader("Authorization") String tokenAdmin) {
        List<ConsultaProjection> consulta = consultaRepository.findAllConsulta();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(consulta);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postConsulta(@RequestBody Consulta consulta, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        consulta.setFechamodificacion(dateNow());
        consulta.setUsuariomodificacion(user);
        consulta.setUsuariocreacion(user);
        consulta.setFechacreacion(dateNow());

        consultaRepository.save(consulta);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(consulta);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateConsulta(@RequestBody Consulta consulta, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Consulta> currentConsulta = consultaRepository.findById(consulta.getIdconsulta());

        if (currentConsulta.isPresent()) {

            consulta.setFechamodificacion(dateNow());
            consulta.setUsuariomodificacion(user);
            consulta.setUsuariocreacion(currentConsulta.get().getUsuariocreacion());
            consulta.setFechacreacion(currentConsulta.get().getFechacreacion());
            consultaRepository.save(consulta);

            response.setCode(200);
            response.setMessage("Consulta Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(consulta);
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
