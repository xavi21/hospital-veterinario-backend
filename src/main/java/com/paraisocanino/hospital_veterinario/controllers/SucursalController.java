package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Sucursal;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.SucursalRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/all")
    public GeneralResponseList getSucursales(@RequestHeader("Authorization") String tokenAdmin) {
        List<Sucursal> sucursales = sucursalRepository.findAll();

        List<Sucursal> sucursalResponse = new ArrayList<>();

        for (Sucursal sucursal : sucursales) {
            Sucursal sucursalr = new Sucursal();
            sucursalr.setIdsucursal(sucursal.getIdsucursal());
            sucursalr.setName(sucursal.getName());
            sucursalr.setDireccion(sucursal.getDireccion());
            sucursalr.setFechacreacion(sucursal.getFechacreacion());
            sucursalr.setUsuariocreacion(sucursal.getUsuariocreacion());
            sucursalr.setFechamodificacion(sucursal.getFechamodificacion());
            sucursalr.setUsuariomodificacion(sucursal.getUsuariomodificacion());

            sucursalResponse.add(sucursalr);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(sucursalResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postSucursales(@RequestBody Sucursal sucursal, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        sucursal.setFechamodificacion(dateNow());
        sucursal.setUsuariomodificacion(user);
        sucursal.setUsuariocreacion(user);
        sucursal.setFechacreacion(dateNow());

        sucursalRepository.save(sucursal);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(sucursal);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse putSucursales(@RequestBody Sucursal sucursal, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        if (sucursalRepository.findById(sucursal.getIdsucursal()).isPresent()) {

            Optional<Sucursal> currenSucursal = sucursalRepository.findById(sucursal.getIdsucursal());

            sucursal.setFechamodificacion(dateNow());
            sucursal.setUsuariomodificacion(user);
            sucursal.setUsuariocreacion(currenSucursal.get().getUsuariocreacion());
            sucursal.setFechacreacion(currenSucursal.get().getFechacreacion());
            sucursalRepository.save(sucursal);

            response.setCode(200);
            response.setMessage("Sucursal Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }
        response.setData(sucursal);
        return response;

    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse EliminaProducto(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Sucursal> currenSucursal = sucursalRepository.findById(id);
        if (currenSucursal.isPresent()) {
            sucursalRepository.delete(currenSucursal.get());
            response.setCode(200);
            response.setMessage("Se eliminó el producto : " + id);

        } else {
            response.setCode(200);
            response.setMessage("No existe el producto :" + id);
        }
        return response;
    }


    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
