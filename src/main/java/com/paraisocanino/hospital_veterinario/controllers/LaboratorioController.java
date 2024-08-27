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

import com.paraisocanino.hospital_veterinario.models.Laboratorio;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.LaboratorioRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllLaboratorios(@RequestHeader("Authorization") String tokenAdmin) {
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        List<Laboratorio> laboratorioResponse = new ArrayList<>();

        for (Laboratorio laboratorio : laboratorios) {
            final Laboratorio model = new Laboratorio();
            model.setIdLaboratorio(laboratorio.getIdLaboratorio());
            model.setNombre(laboratorio.getNombre());
            model.setDescripcion(laboratorio.getDescripcion());
            model.setUsuariocreacion(laboratorio.getUsuariocreacion());
            model.setFechacreacion(laboratorio.getFechacreacion());
            model.setUsuariomodificacion(laboratorio.getUsuariomodificacion());
            model.setFechamodificacion(laboratorio.getFechamodificacion());

            laboratorioResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(laboratorioResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createLaboratorio(@RequestBody Laboratorio laboratorio, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        laboratorio.setFechamodificacion(dateNow());
        laboratorio.setUsuariomodificacion(user);
        laboratorio.setUsuariocreacion(user);
        laboratorio.setFechacreacion(dateNow());

        laboratorioRepository.save(laboratorio);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(laboratorio);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateLaboratorio(@RequestBody Laboratorio laboratorio, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Laboratorio> currentStatusEmpleado = laboratorioRepository.findById(laboratorio.getIdLaboratorio());

        if (currentStatusEmpleado.isPresent()) {

            laboratorio.setFechamodificacion(dateNow());
            laboratorio.setUsuariomodificacion(user);
            laboratorio.setUsuariocreacion(currentStatusEmpleado.get().getUsuariocreacion());
            laboratorio.setFechacreacion(currentStatusEmpleado.get().getFechacreacion());
            laboratorioRepository.save(laboratorio);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(laboratorio);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteLaboratorio(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Laboratorio> currentLaboratorio= laboratorioRepository.findById(id);

        if (currentLaboratorio.isPresent()) {
            laboratorioRepository.delete(currentLaboratorio.get());
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
