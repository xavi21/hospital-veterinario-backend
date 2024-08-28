package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.StatusUsuario;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.StatusUsuarioRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/statususuario")
public class StatusUsuarioController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StatusUsuarioRepository statusUsuarioRepository;

    @GetMapping("/all")
    public GeneralResponseList getStatusUsuario(@RequestHeader("Authorization") String tokenAdmin) {
        List<StatusUsuario> statusUsuarios = statusUsuarioRepository.findAll();

        List<StatusUsuario> statusUsuarioResponse = new ArrayList<>();

        for (StatusUsuario statusUsuario : statusUsuarios) {
            final StatusUsuario statusUsuarior = new StatusUsuario();
            statusUsuarior.setIdstatususuario(statusUsuario.getIdstatususuario());
            statusUsuarior.setName(statusUsuario.getName());
            statusUsuarior.setUsuariocreacion(statusUsuario.getUsuariocreacion());
            statusUsuarior.setFechacreacion(statusUsuario.getFechacreacion());
            statusUsuarior.setUsuariomodificacion(statusUsuario.getUsuariomodificacion());
            statusUsuarior.setFechamodificacion(statusUsuario.getFechamodificacion());

            statusUsuarioResponse.add(statusUsuarior);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(statusUsuarioResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postStatusUsuario(@RequestBody StatusUsuario statusUsuario, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        statusUsuario.setFechamodificacion(dateNow());
        statusUsuario.setUsuariomodificacion(user);
        statusUsuario.setUsuariocreacion(user);
        statusUsuario.setFechacreacion(dateNow());

        statusUsuarioRepository.save(statusUsuario);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(statusUsuario);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse putStatusUsuario(@RequestBody StatusUsuario statusUsuario, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        if (statusUsuarioRepository.findById(statusUsuario.getIdstatususuario()).isPresent()) {

            Optional<StatusUsuario> currenStatusUsuario = statusUsuarioRepository.findById(statusUsuario.getIdstatususuario());

            statusUsuario.setFechamodificacion(dateNow());
            statusUsuario.setUsuariomodificacion(user);
            statusUsuario.setUsuariocreacion(currenStatusUsuario.get().getUsuariocreacion());
            statusUsuario.setFechacreacion(currenStatusUsuario.get().getFechacreacion());
            statusUsuarioRepository.save(statusUsuario);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }
        response.setData(statusUsuario);
        return response;

    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse eliminaStatusUsuario(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<StatusUsuario> currenStatusUsuario = statusUsuarioRepository.findById(id);
        if (currenStatusUsuario.isPresent()) {
            statusUsuarioRepository.delete(currenStatusUsuario.get());
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
