package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.HospitalizacionLaboratorio;
import com.paraisocanino.hospital_veterinario.models.HospitalizacionMedicamento;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionLaboratorioProjection;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionLaboratorioRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hospitalizacionlaboratorio")
public class HospitalizacionLaboratorioController {

    @Autowired
    private HospitalizacionLaboratorioRepository hospitalizacionLaboratorioRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getHospitalizacionLaboratorio(@RequestHeader("Authorization") String tokenAdmin) {
        List<HospitalizacionLaboratorioProjection> hospitalizacionLaboratorios = hospitalizacionLaboratorioRepository.findAllHospitalLaboratorio();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(hospitalizacionLaboratorios);

        return response;
    }

    @GetMapping("/laboratorioByHospitalizacion")
    public GeneralResponseList getHospitalizacionByLaboratorio(@RequestHeader("Authorization") String tokenAdmin, @RequestParam Integer idhospitalizacion) {
        List<HospitalizacionLaboratorioProjection> hospitalizacionLaboratorio = hospitalizacionLaboratorioRepository.findAllHospitalByLaboratorio(idhospitalizacion);


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(hospitalizacionLaboratorio);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postHopitalizacionLaboratorio(@RequestBody HospitalizacionLaboratorio hospitalizacionLaboratorio, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        hospitalizacionLaboratorio.setFechamodificacion(dateNow());
        hospitalizacionLaboratorio.setUsuariomodificacion(user);
        hospitalizacionLaboratorio.setUsuariocreacion(user);
        hospitalizacionLaboratorio.setFechacreacion(dateNow());

        hospitalizacionLaboratorioRepository.save(hospitalizacionLaboratorio);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(hospitalizacionLaboratorio);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateHospitalizacionLaboratorio(@RequestBody HospitalizacionLaboratorio hospitalizacionLaboratorio, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<HospitalizacionLaboratorio> currentHospitalizacionLaboratorio = hospitalizacionLaboratorioRepository.findById(hospitalizacionLaboratorio.getIdhospitalizacionlaboratorio());

        if (currentHospitalizacionLaboratorio.isPresent()) {

            hospitalizacionLaboratorio.setFechamodificacion(dateNow());
            hospitalizacionLaboratorio.setUsuariomodificacion(user);
            hospitalizacionLaboratorio.setUsuariocreacion(currentHospitalizacionLaboratorio.get().getUsuariocreacion());
            hospitalizacionLaboratorio.setFechacreacion(currentHospitalizacionLaboratorio.get().getFechacreacion());
            hospitalizacionLaboratorioRepository.save(hospitalizacionLaboratorio);

            response.setCode(200);
            response.setMessage("Hospitalizacion Medicamento Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(hospitalizacionLaboratorio);
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }

}
