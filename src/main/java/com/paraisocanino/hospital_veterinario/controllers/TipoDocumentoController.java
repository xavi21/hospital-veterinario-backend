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

import com.paraisocanino.hospital_veterinario.models.Talla;
import com.paraisocanino.hospital_veterinario.models.TipoDocumento;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.TallaRepository;
import com.paraisocanino.hospital_veterinario.repository.TipoDocumentoRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tipo-documento")
public class TipoDocumentoController {
@Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @GetMapping("/all")
    public GeneralResponseList getAllTipoDocumento(@RequestHeader("Authorization") String tokenAdmin) {
        List<TipoDocumento> tiposDocumento = tipoDocumentoRepository.findAll();
        List<TipoDocumento> tipoDocumentoResponse = new ArrayList<>();

        for (TipoDocumento tipoDocumento : tiposDocumento) {
            final TipoDocumento model = new TipoDocumento();
            model.setIdTipoDocumento(tipoDocumento.getIdTipoDocumento());
            model.setNombre(tipoDocumento.getNombre());
            model.setUsuariocreacion(tipoDocumento.getUsuariocreacion());
            model.setFechacreacion(tipoDocumento.getFechacreacion());
            model.setUsuariomodificacion(tipoDocumento.getUsuariomodificacion());
            model.setFechamodificacion(tipoDocumento.getFechamodificacion());

            tipoDocumentoResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(tipoDocumentoResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createTipoDocumento(@RequestBody TipoDocumento tipoDocumento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        tipoDocumento.setFechamodificacion(dateNow());
        tipoDocumento.setUsuariomodificacion(user);
        tipoDocumento.setUsuariocreacion(user);
        tipoDocumento.setFechacreacion(dateNow());

        tipoDocumentoRepository.save(tipoDocumento);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(tipoDocumento);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateTipoDocumento(@RequestBody TipoDocumento tipoDocumento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<TipoDocumento> currentTipoDocumento = tipoDocumentoRepository.findById(tipoDocumento.getIdTipoDocumento());

        if (currentTipoDocumento.isPresent()) {

            tipoDocumento.setFechamodificacion(dateNow());
            tipoDocumento.setUsuariomodificacion(user);
            tipoDocumento.setUsuariocreacion(currentTipoDocumento.get().getUsuariocreacion());
            tipoDocumento.setFechacreacion(currentTipoDocumento.get().getFechacreacion());
            tipoDocumentoRepository.save(tipoDocumento);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(tipoDocumento);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteTipoDocumento(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<TipoDocumento> currentTipoDocumento = tipoDocumentoRepository.findById(id);

        if (currentTipoDocumento.isPresent()) {
            tipoDocumentoRepository.delete(currentTipoDocumento.get());
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
