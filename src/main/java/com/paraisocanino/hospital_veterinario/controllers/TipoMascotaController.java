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

import com.paraisocanino.hospital_veterinario.models.TipoMascota;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.TipoMascotaReponsitory;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tipo-mascota")
public class TipoMascotaController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TipoMascotaReponsitory tipoMascotaReponsitory;

    @GetMapping("/all")
    public GeneralResponseList getAllTiposMascotas(@RequestHeader("Authorization") String tokenAdmin) {
        List<TipoMascota> tiposMascotas = tipoMascotaReponsitory.findAll();
        List<TipoMascota> tipoMascotaResponse = new ArrayList<>();

        for (TipoMascota talla : tiposMascotas) {
            final TipoMascota model = new TipoMascota();
            model.setIdTipoMascota(talla.getIdTipoMascota());
            model.setNombre(talla.getNombre());
            model.setUsuariocreacion(talla.getUsuariocreacion());
            model.setFechacreacion(talla.getFechacreacion());
            model.setUsuariomodificacion(talla.getUsuariomodificacion());
            model.setFechamodificacion(talla.getFechamodificacion());

            tipoMascotaResponse.add(model);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(tipoMascotaResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse createTipoMascota(@RequestBody TipoMascota tipoMascota, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        tipoMascota.setFechamodificacion(dateNow());
        tipoMascota.setUsuariomodificacion(user);
        tipoMascota.setUsuariocreacion(user);
        tipoMascota.setFechacreacion(dateNow());

        tipoMascotaReponsitory.save(tipoMascota);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(tipoMascota);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateTalla(@RequestBody TipoMascota tipoMascota, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<TipoMascota> currentTipoMascota = tipoMascotaReponsitory.findById(tipoMascota.getIdTipoMascota());

        if (currentTipoMascota.isPresent()) {

            tipoMascota.setFechamodificacion(dateNow());
            tipoMascota.setUsuariomodificacion(user);
            tipoMascota.setUsuariocreacion(currentTipoMascota.get().getUsuariocreacion());
            tipoMascota.setFechacreacion(currentTipoMascota.get().getFechacreacion());
            tipoMascotaReponsitory.save(tipoMascota);

            response.setCode(200);
            response.setMessage("Status Usuario Actualizado");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(tipoMascota);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deleteTalla(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<TipoMascota> currentTipoMascota = tipoMascotaReponsitory.findById(id);

        if (currentTipoMascota.isPresent()) {
            tipoMascotaReponsitory.delete(currentTipoMascota.get());
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
