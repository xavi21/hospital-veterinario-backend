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

import com.paraisocanino.hospital_veterinario.models.CasaMedica;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.CasaMedicaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/casa-medica")
public class CasaMedicaController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CasaMedicaRepository casaMedicaRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllCasaMedicas(@RequestHeader("Authorization") String tokenAdmin) {
        List<CasaMedica> casasMedicas = casaMedicaRepository.findAll();
        List<CasaMedica> casaMedicaResponse = new ArrayList<>();

        for (CasaMedica casaMedica : casasMedicas) {
            final CasaMedica model = new CasaMedica();
            model.setIdcasamedica(casaMedica.getIdcasamedica());
            model.setNombre(casaMedica.getNombre());
            model.setNombrecomercial(casaMedica.getNombrecomercial());
            model.setUsuariocreacion(casaMedica.getUsuariocreacion());
            model.setFechacreacion(casaMedica.getFechacreacion());
            model.setUsuariomodificacion(casaMedica.getUsuariomodificacion());
            model.setFechamodificacion(casaMedica.getFechamodificacion());

            casaMedicaResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(casaMedicaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createCasaMedica(@RequestBody CasaMedica casaMedica, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        casaMedica.setFechamodificacion(dateNow());
        casaMedica.setUsuariomodificacion(user);
        casaMedica.setUsuariocreacion(user);
        casaMedica.setFechacreacion(dateNow());

        casaMedicaRepository.save(casaMedica);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(casaMedica);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateCasaMedica(@RequestBody CasaMedica casaMedica, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<CasaMedica> currentCasaMedica = casaMedicaRepository.findById(casaMedica.getIdcasamedica());

        if (currentCasaMedica.isPresent()) {
            casaMedica.setFechamodificacion(dateNow());
            casaMedica.setUsuariomodificacion(user);
            casaMedica.setUsuariocreacion(currentCasaMedica.get().getUsuariocreacion());
            casaMedica.setFechacreacion(currentCasaMedica.get().getFechacreacion());
            casaMedicaRepository.save(casaMedica);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(casaMedica);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteCasaMedica(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<CasaMedica> currentCasaMedica = casaMedicaRepository.findById(id);

        if (currentCasaMedica.isPresent()) {
            casaMedicaRepository.delete(currentCasaMedica.get());
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
