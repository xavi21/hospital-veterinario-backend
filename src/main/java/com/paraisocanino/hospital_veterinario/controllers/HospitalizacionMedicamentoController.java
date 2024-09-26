package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.HospitalizacionMedicamento;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionMedicamentoProjection;
import com.paraisocanino.hospital_veterinario.repository.HospitalizacionMedicamentoRepository;
import com.paraisocanino.hospital_veterinario.repository.UsuarioMenuOpcionProjection;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hospitalizacionmedicamento")
public class HospitalizacionMedicamentoController {

    @Autowired
    private HospitalizacionMedicamentoRepository hospitalizacionMedicamentoRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getPersonas(@RequestHeader("Authorization") String tokenAdmin) {
        List<HospitalizacionMedicamentoProjection> hospitalizacionMedicamento = hospitalizacionMedicamentoRepository.findAllHospitalMedicamento();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(hospitalizacionMedicamento);

        return response;
    }

    @GetMapping("/medicamentoByHospitalizacion")
    public GeneralResponseList getUsuarioMenuByUsuario(@RequestHeader("Authorization") String tokenAdmin, @RequestParam Integer idhospitalizacion) {
        List<HospitalizacionMedicamentoProjection> hospitalizacionMedicamento = hospitalizacionMedicamentoRepository.findmedicamentoByHospitalizacion(idhospitalizacion);


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(hospitalizacionMedicamento);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postPersona(@RequestBody HospitalizacionMedicamento hospitalizacionMedicamento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        hospitalizacionMedicamento.setFechamodificacion(dateNow());
        hospitalizacionMedicamento.setUsuariomodificacion(user);
        hospitalizacionMedicamento.setUsuariocreacion(user);
        hospitalizacionMedicamento.setFechacreacion(dateNow());

        hospitalizacionMedicamentoRepository.save(hospitalizacionMedicamento);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(hospitalizacionMedicamento);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateHospitalizacionMedicamento(@RequestBody HospitalizacionMedicamento hospitalizacionMedicamento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<HospitalizacionMedicamento> currentHospitalizacionMedicamento = hospitalizacionMedicamentoRepository.findById(hospitalizacionMedicamento.getIdhospitalizacionmedicamento());

        if (currentHospitalizacionMedicamento.isPresent()) {

            hospitalizacionMedicamento.setFechamodificacion(dateNow());
            hospitalizacionMedicamento.setUsuariomodificacion(user);
            hospitalizacionMedicamento.setUsuariocreacion(currentHospitalizacionMedicamento.get().getUsuariocreacion());
            hospitalizacionMedicamento.setFechacreacion(currentHospitalizacionMedicamento.get().getFechacreacion());
            hospitalizacionMedicamentoRepository.save(hospitalizacionMedicamento);

            response.setCode(200);
            response.setMessage("Hospitalizacion Medicamento Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(hospitalizacionMedicamento);
        return response;
    }


    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
