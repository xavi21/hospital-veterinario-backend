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

import com.paraisocanino.hospital_veterinario.models.StatusEmpleado;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.StatusEmpleadoRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/statusempleado")
public class StatusEmpleadoController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StatusEmpleadoRepository statusEmpleadoRepository;

    @GetMapping("/all")
    public GeneralResponseList getStatusUsuario(@RequestHeader("Authorization") String tokenAdmin) {
        List<StatusEmpleado> statusEmpleados = statusEmpleadoRepository.findAll();

        List<StatusEmpleado> statusEmpleadoResponse = new ArrayList<>();

        for (StatusEmpleado statusEmpleado : statusEmpleados) {
            final StatusEmpleado model = new StatusEmpleado();
            model.setIdStatusEmpleado(statusEmpleado.getIdStatusEmpleado());
            model.setName(statusEmpleado.getName());
            model.setUsuariocreacion(statusEmpleado.getUsuariocreacion());
            model.setFechacreacion(statusEmpleado.getFechacreacion());
            model.setUsuariomodificacion(statusEmpleado.getUsuariomodificacion());
            model.setFechamodificacion(statusEmpleado.getFechamodificacion());

            statusEmpleadoResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(statusEmpleadoResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postStatusUsuario(@RequestBody StatusEmpleado statusEmpleado, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        statusEmpleado.setFechamodificacion(dateNow());
        statusEmpleado.setUsuariomodificacion(user);
        statusEmpleado.setUsuariocreacion(user);
        statusEmpleado.setFechacreacion(dateNow());

        statusEmpleadoRepository.save(statusEmpleado);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(statusEmpleado);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse putStatusUsuario(@RequestBody StatusEmpleado statusEmpleado, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<StatusEmpleado> currentStatusEmpleado = statusEmpleadoRepository.findById(statusEmpleado.getIdStatusEmpleado());

        if (currentStatusEmpleado.isPresent()) {

            statusEmpleado.setFechamodificacion(dateNow());
            statusEmpleado.setUsuariomodificacion(user);
            statusEmpleado.setUsuariocreacion(currentStatusEmpleado.get().getUsuariocreacion());
            statusEmpleado.setFechacreacion(currentStatusEmpleado.get().getFechacreacion());
            statusEmpleadoRepository.save(statusEmpleado);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(statusEmpleado);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse eliminaStatusUsuario(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<StatusEmpleado> currentStatusEmpleado = statusEmpleadoRepository.findById(id);

        if (currentStatusEmpleado.isPresent()) {
            statusEmpleadoRepository.delete(currentStatusEmpleado.get());
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
