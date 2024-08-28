package com.paraisocanino.hospital_veterinario.controllers;


import com.paraisocanino.hospital_veterinario.models.Opcion;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.OpcionRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/opcion")
public class OpcionController {
    @Autowired
    private OpcionRepository opcionRepository;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/all")
    public GeneralResponseList opciones(@RequestHeader("Authorization") String tokenAdmi) {
        List<Opcion> opcions = opcionRepository.findAll();

        List<Opcion> opcionResponse = new ArrayList<>();


        for (Opcion opcion : opcions) {
            Opcion opcionr = new Opcion();
            opcionr.setIdopcion(opcion.getIdopcion());
            opcionr.setName(opcion.getName());
            opcionr.setOrdenmenu(opcion.getOrdenmenu());
            opcionr.setUsuariocreacion(opcion.getUsuariocreacion());
            opcionr.setFechacreacion(opcion.getFechacreacion());
            opcionr.setUsuariomodificacion(opcion.getUsuariomodificacion());
            opcionr.setFechamodificacion(opcion.getFechamodificacion());

            opcionResponse.add(opcionr);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(opcionResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postOpcion(@RequestBody Opcion opcion, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        opcion.setFechamodificacion(dateNow());
        opcion.setUsuariomodificacion(user);
        opcion.setUsuariocreacion(user);
        opcion.setFechacreacion(dateNow());

        opcionRepository.save(opcion);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(opcion);

        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }


    @PutMapping("/update")
    public GeneralResponse putOpcion(@RequestBody Opcion opcion, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        if (opcionRepository.findById(opcion.getIdopcion()).isPresent()) {

            Optional<Opcion> currentOpcion = opcionRepository.findById(opcion.getIdopcion());

            opcion.setFechamodificacion(dateNow());
            opcion.setUsuariomodificacion(user);
            opcion.setUsuariocreacion(currentOpcion.get().getUsuariocreacion());
            opcion.setFechacreacion(currentOpcion.get().getFechacreacion());
            opcionRepository.save(opcion);

            response.setCode(200);
            response.setMessage("Opcion Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }
        response.setData(opcion);
        return response;

    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse EliminarOpcion(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Opcion> currenOpcion = opcionRepository.findById(id);
        if (currenOpcion.isPresent()) {
            opcionRepository.delete(currenOpcion.get());
            response.setCode(200);
            response.setMessage("Se eliminó la opción : " + id);

        } else {
            response.setCode(200);
            response.setMessage("No existe la opción :" + id);
        }
        return response;
    }

}
