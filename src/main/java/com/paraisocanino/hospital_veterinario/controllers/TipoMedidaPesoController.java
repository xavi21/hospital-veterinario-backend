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

import com.paraisocanino.hospital_veterinario.models.TipoMedidaPeso;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.TipoMedidaPesoRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tipo-medida-peso")
public class TipoMedidaPesoController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TipoMedidaPesoRepository tipoMedidaPesoRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllTipoMedidaPeso(@RequestHeader("Authorization") String tokenAdmin) {
        List<TipoMedidaPeso> tipoMedidaPesos = tipoMedidaPesoRepository.findAll();
        List<TipoMedidaPeso> tipoMedidaPesoResponse = new ArrayList<>();

        for (TipoMedidaPeso tipoMedidaPeso : tipoMedidaPesos) {
            final TipoMedidaPeso model = new TipoMedidaPeso();
            model.setIdTipoMedidaPeso(tipoMedidaPeso.getIdTipoMedidaPeso());
            model.setNombre(tipoMedidaPeso.getNombre());
            model.setUsuariocreacion(tipoMedidaPeso.getUsuariocreacion());
            model.setFechacreacion(tipoMedidaPeso.getFechacreacion());
            model.setUsuariomodificacion(tipoMedidaPeso.getUsuariomodificacion());
            model.setFechamodificacion(tipoMedidaPeso.getFechamodificacion());

            tipoMedidaPesoResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(tipoMedidaPesoResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createTipoMedidaPeso(@RequestBody TipoMedidaPeso tipoDocumento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        tipoDocumento.setFechamodificacion(dateNow());
        tipoDocumento.setUsuariomodificacion(user);
        tipoDocumento.setUsuariocreacion(user);
        tipoDocumento.setFechacreacion(dateNow());

        tipoMedidaPesoRepository.save(tipoDocumento);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(tipoDocumento);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateTipoMedidaPeso(@RequestBody TipoMedidaPeso tipoMedidaPeso, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<TipoMedidaPeso> currentTipoMedidaPeso = tipoMedidaPesoRepository.findById(tipoMedidaPeso.getIdTipoMedidaPeso());

        if (currentTipoMedidaPeso.isPresent()) {

            tipoMedidaPeso.setFechamodificacion(dateNow());
            tipoMedidaPeso.setUsuariomodificacion(user);
            tipoMedidaPeso.setUsuariocreacion(currentTipoMedidaPeso.get().getUsuariocreacion());
            tipoMedidaPeso.setFechacreacion(currentTipoMedidaPeso.get().getFechacreacion());
            tipoMedidaPesoRepository.save(tipoMedidaPeso);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(tipoMedidaPeso);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteTipoMedidaPeso(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<TipoMedidaPeso> currentTipoMedidaPeso = tipoMedidaPesoRepository.findById(id);

        if (currentTipoMedidaPeso.isPresent()) {
            tipoMedidaPesoRepository.delete(currentTipoMedidaPeso.get());
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
