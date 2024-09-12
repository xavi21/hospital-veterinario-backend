package com.paraisocanino.hospital_veterinario.controllers;


import com.paraisocanino.hospital_veterinario.models.Medicamento;
import com.paraisocanino.hospital_veterinario.models.Persona;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.MedicamentoProjection;
import com.paraisocanino.hospital_veterinario.repository.MedicamentoRepository;
import com.paraisocanino.hospital_veterinario.repository.PersonaProjection;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/medicamento")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getMedicamento(@RequestHeader("Authorization") String tokenAdmin) {
        List<MedicamentoProjection> medicamentos = medicamentoRepository.findAllMedicamento();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(medicamentos);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postMedicamento(@RequestBody Medicamento medicamento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        medicamento.setFechamodificacion(dateNow());
        medicamento.setUsuariomodificacion(user);
        medicamento.setUsuariocreacion(user);
        medicamento.setFechacreacion(dateNow());

        medicamentoRepository.save(medicamento);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(medicamento);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateMedicamento(@RequestBody Medicamento medicamento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Medicamento> currentMedicamento = medicamentoRepository.findById(medicamento.getIdmedicamento());

        if (currentMedicamento.isPresent()) {

            medicamento.setFechamodificacion(dateNow());
            medicamento.setUsuariomodificacion(user);
            medicamento.setUsuariocreacion(currentMedicamento.get().getUsuariocreacion());
            medicamento.setFechacreacion(currentMedicamento.get().getFechacreacion());
            medicamentoRepository.save(medicamento);

            response.setCode(200);
            response.setMessage("Medicamento Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(medicamento);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteMeicamento(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Medicamento> currentMedicamento = medicamentoRepository.findById(id);

        if (currentMedicamento.isPresent()) {
            medicamentoRepository.delete(currentMedicamento.get());
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
