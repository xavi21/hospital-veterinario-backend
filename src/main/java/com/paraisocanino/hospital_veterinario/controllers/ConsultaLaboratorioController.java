package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.ConsultaLaboratorio;
import com.paraisocanino.hospital_veterinario.models.HospitalizacionLaboratorio;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.ConsultaLaboratorioProjection;
import com.paraisocanino.hospital_veterinario.repository.ConsultaLaboratorioRepository;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionLaboratorioProjection;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/consultalaboratorio")
public class ConsultaLaboratorioController {

    @Autowired
    private ConsultaLaboratorioRepository consultaLaboratorioRepository;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/all")
    public GeneralResponseList getHospitalizacionLaboratorio(@RequestHeader("Authorization") String tokenAdmin) {
        List<ConsultaLaboratorioProjection> consultaLaboratorio = consultaLaboratorioRepository.findAllConsultaLaboratorio();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(consultaLaboratorio);

        return response;
    }

    @GetMapping("/laboratorioByConsulta")
    public GeneralResponseList getConsultaByLaboratorio(@RequestHeader("Authorization") String tokenAdmin, @RequestParam Integer idconsulta) {
        List<ConsultaLaboratorioProjection> consultaLaboratorio = consultaLaboratorioRepository.findAllLaboratorioByConsulta(idconsulta);


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(consultaLaboratorio);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postConsultaLaboratorio(@RequestBody ConsultaLaboratorio consultaLaboratorio, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        consultaLaboratorio.setFechamodificacion(dateNow());
        consultaLaboratorio.setUsuariomodificacion(user);
        consultaLaboratorio.setUsuariocreacion(user);
        consultaLaboratorio.setFechacreacion(dateNow());

        consultaLaboratorioRepository.save(consultaLaboratorio);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(consultaLaboratorio);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateConsultaLaboratorio(@RequestBody ConsultaLaboratorio consultaLaboratorio, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<ConsultaLaboratorio> currentConsultaLaboratorio = consultaLaboratorioRepository.findById(consultaLaboratorio.getIdconsultalaboratorio());

        if (currentConsultaLaboratorio.isPresent()) {

            consultaLaboratorio.setFechamodificacion(dateNow());
            consultaLaboratorio.setUsuariomodificacion(user);
            consultaLaboratorio.setUsuariocreacion(currentConsultaLaboratorio.get().getUsuariocreacion());
            consultaLaboratorio.setFechacreacion(currentConsultaLaboratorio.get().getFechacreacion());
            consultaLaboratorioRepository.save(consultaLaboratorio);

            response.setCode(200);
            response.setMessage("Consulta Laboratorio Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(consultaLaboratorio);
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
