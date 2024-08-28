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

import com.paraisocanino.hospital_veterinario.models.ComponentePrincipal;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.ComponentePrincipalRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/componente-principal")
public class ComponentePrincipalController {
@Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ComponentePrincipalRepository componentePrincipalRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllComponentePrincipal(@RequestHeader("Authorization") String tokenAdmin) {
        List<ComponentePrincipal> componentesPrincipales = componentePrincipalRepository.findAll();
        List<ComponentePrincipal> componentePrincipalResponse = new ArrayList<>();

        for (ComponentePrincipal componentePrincipal : componentesPrincipales) {
            final ComponentePrincipal model = new ComponentePrincipal();
            model.setIdComponentePrincipal(componentePrincipal.getIdComponentePrincipal());
            model.setNombre(componentePrincipal.getNombre());
            model.setUsuariocreacion(componentePrincipal.getUsuariocreacion());
            model.setFechacreacion(componentePrincipal.getFechacreacion());
            model.setUsuariomodificacion(componentePrincipal.getUsuariomodificacion());
            model.setFechamodificacion(componentePrincipal.getFechamodificacion());

            componentePrincipalResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(componentePrincipalResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createComponentePrincipal(@RequestBody ComponentePrincipal componentePrincipal, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        componentePrincipal.setFechamodificacion(dateNow());
        componentePrincipal.setUsuariomodificacion(user);
        componentePrincipal.setUsuariocreacion(user);
        componentePrincipal.setFechacreacion(dateNow());

        componentePrincipalRepository.save(componentePrincipal);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(componentePrincipal);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateComponentePrincipal(@RequestBody ComponentePrincipal componentePrincipal, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<ComponentePrincipal> currentComponentePrincipal = componentePrincipalRepository.findById(componentePrincipal.getIdComponentePrincipal());

        if (currentComponentePrincipal.isPresent()) {

            componentePrincipal.setFechamodificacion(dateNow());
            componentePrincipal.setUsuariomodificacion(user);
            componentePrincipal.setUsuariocreacion(currentComponentePrincipal.get().getUsuariocreacion());
            componentePrincipal.setFechacreacion(currentComponentePrincipal.get().getFechacreacion());
            componentePrincipalRepository.save(componentePrincipal);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(componentePrincipal);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteComponentePrincipal(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<ComponentePrincipal> currentComponentePrincipal = componentePrincipalRepository.findById(id);

        if (currentComponentePrincipal.isPresent()) {
            componentePrincipalRepository.delete(currentComponentePrincipal.get());
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
