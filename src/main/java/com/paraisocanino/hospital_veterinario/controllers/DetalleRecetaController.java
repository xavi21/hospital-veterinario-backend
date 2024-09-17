package com.paraisocanino.hospital_veterinario.controllers;


import com.paraisocanino.hospital_veterinario.models.DetalleReceta;
import com.paraisocanino.hospital_veterinario.payload.DetalleRecetaCompuesta;
import com.paraisocanino.hospital_veterinario.payload.request.DetalleRecetaRequest;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.DetalleRecetaProjection;
import com.paraisocanino.hospital_veterinario.repository.DetallerecetaRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/detalleReceta")
public class DetalleRecetaController {

    @Autowired
    private DetallerecetaRepository detallerecetaRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getDetalleReceta(@RequestHeader("Authorization") String tokenAdmin) {
        List<DetalleRecetaProjection> personas = detallerecetaRepository.findAllDetalleReceta();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(personas);

        return response;
    }

    @GetMapping("/receta")
    public GeneralResponseList getDetalleByReceta(@RequestHeader("Authorization") String tokenAdmin, @RequestParam Integer idreceta) {
        List<DetalleRecetaProjection> personas = detallerecetaRepository.findMeciamentobyReceta(idreceta);

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(personas);

        return response;
    }


    @PostMapping("/save")
    public GeneralResponse postDetalleReceta(@RequestBody DetalleRecetaRequest detalleRecetaRequest, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final DetalleReceta detalleReceta = new DetalleReceta();
        final DetalleRecetaCompuesta detalleRecetaCompuesta = new DetalleRecetaCompuesta();

        detalleRecetaCompuesta.setIdreceta(detalleRecetaRequest.getIdreceta());
        detalleRecetaCompuesta.setIdmedicamento(detalleRecetaRequest.getIdmedicamento());

        detalleReceta.setId(detalleRecetaCompuesta);
        detalleReceta.setCantidad(detalleRecetaRequest.getCantidad());
        detalleReceta.setIndicaciones(detalleRecetaRequest.getIndicaciones());
        detalleReceta.setFechamodificacion(dateNow());
        detalleReceta.setUsuariomodificacion(user);
        detalleReceta.setUsuariocreacion(user);
        detalleReceta.setFechacreacion(dateNow());

        detallerecetaRepository.save(detalleReceta);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(detalleReceta);

        return response;
    }

    @DeleteMapping("/delete")
    public GeneralResponse putDetalleReceta(@RequestParam Integer idreceta, @RequestParam Integer idmedicamento, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();
        final DetalleReceta detalleReceta = new DetalleReceta();
        final DetalleRecetaCompuesta detalleRecetaCompuesta = new DetalleRecetaCompuesta();

        detalleRecetaCompuesta.setIdreceta(idreceta);
        detalleRecetaCompuesta.setIdmedicamento(idmedicamento);

        if (detallerecetaRepository.findById(detalleRecetaCompuesta).isPresent()) {
            Optional<DetalleReceta> currentDetalleReceta = detallerecetaRepository.findById(detalleRecetaCompuesta);

            detallerecetaRepository.delete(currentDetalleReceta.get());
            response.setCode(200);
            response.setMessage("Registro Eliminado");
        } else {
            response.setCode(400);
            response.setMessage("No existe el Registro");
        }
        response.setData(detalleReceta);


        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }

}
