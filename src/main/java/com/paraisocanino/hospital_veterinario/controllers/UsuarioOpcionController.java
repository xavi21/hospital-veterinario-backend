package com.paraisocanino.hospital_veterinario.controllers;


import com.paraisocanino.hospital_veterinario.models.UsuarioOpcion;
import com.paraisocanino.hospital_veterinario.payload.LlaveCompuesta;
import com.paraisocanino.hospital_veterinario.payload.request.UsuarioOpcionRequest;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.UsuarioMenuOpcionProjection;
import com.paraisocanino.hospital_veterinario.repository.UsuarioOpcionRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usuarioOpcion")
public class UsuarioOpcionController {

    @Autowired
    private UsuarioOpcionRepository usuarioOpcionRepository;


    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getUsuarioOpcion(@RequestHeader("Authorization") String tokenAdmin) {
        List<UsuarioMenuOpcionProjection> usuarioMenuOpcions = usuarioOpcionRepository.findAllMenu();


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(usuarioMenuOpcions);

        return response;
    }

    @GetMapping("/menuByUser")
    public GeneralResponseList getUsuarioMenuByUsuario(@RequestHeader("Authorization") String tokenAdmin, @RequestParam String idUsuario) {
        List<UsuarioMenuOpcionProjection> usuarioMenuOpcions = usuarioOpcionRepository.findMenuByUsuario(idUsuario);


        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(usuarioMenuOpcions);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postUsuarioOpcion(@RequestBody UsuarioOpcionRequest usuarioOpcionRequest, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final UsuarioOpcion usuarioOpcion = new UsuarioOpcion();
        final LlaveCompuesta llaveCompuesta = new LlaveCompuesta();

        llaveCompuesta.setIdusuario(usuarioOpcionRequest.getIdUsuario());
        llaveCompuesta.setIdmenu(usuarioOpcionRequest.getIdMenu());
        llaveCompuesta.setIdopcion(usuarioOpcionRequest.getIdOpcion());

        usuarioOpcion.setId(llaveCompuesta);
        usuarioOpcion.setAlta(usuarioOpcionRequest.getAlta());
        usuarioOpcion.setBaja(usuarioOpcionRequest.getBaja());
        usuarioOpcion.setCambio(usuarioOpcionRequest.getCambio());
        usuarioOpcion.setFechamodificacion(dateNow());
        usuarioOpcion.setUsuariomodificacion(user);
        usuarioOpcion.setUsuariocreacion(user);
        usuarioOpcion.setFechacreacion(dateNow());

        usuarioOpcionRepository.save(usuarioOpcion);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(usuarioOpcion);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse putUsuarioOpcion(@RequestBody UsuarioOpcionRequest usuarioOpcionRequest, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();
        final LlaveCompuesta llaveCompuesta = new LlaveCompuesta();
        final UsuarioOpcion usuarioOpcion = new UsuarioOpcion();

        llaveCompuesta.setIdusuario(usuarioOpcionRequest.getIdUsuario());
        llaveCompuesta.setIdmenu(usuarioOpcionRequest.getIdMenu());
        llaveCompuesta.setIdopcion(usuarioOpcionRequest.getIdOpcion());

        if (usuarioOpcionRepository.findById(llaveCompuesta).isPresent()) {

            Optional<UsuarioOpcion> currenUsuarioOpcion = usuarioOpcionRepository.findById(llaveCompuesta);

            usuarioOpcion.setId(llaveCompuesta);
            usuarioOpcion.setAlta(usuarioOpcionRequest.getAlta());
            usuarioOpcion.setBaja(usuarioOpcionRequest.getBaja());
            usuarioOpcion.setCambio(usuarioOpcionRequest.getCambio());
            usuarioOpcion.setFechamodificacion(dateNow());
            usuarioOpcion.setUsuariomodificacion(user);
            usuarioOpcion.setUsuariocreacion(currenUsuarioOpcion.get().getUsuariocreacion());
            usuarioOpcion.setFechacreacion(currenUsuarioOpcion.get().getFechacreacion());
            usuarioOpcionRepository.save(usuarioOpcion);

            response.setCode(200);
            response.setMessage("Informacion Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }
        response.setData(usuarioOpcion);
        return response;

    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse EliminaProducto(@RequestParam String idUsuario, @RequestParam Integer idMenu, @RequestParam Integer idOpcion) {
        final GeneralResponse response = new GeneralResponse();
        final LlaveCompuesta llaveCompuesta = new LlaveCompuesta();

        llaveCompuesta.setIdusuario(idUsuario);
        llaveCompuesta.setIdmenu(idMenu);
        llaveCompuesta.setIdopcion(idOpcion);

        Optional<UsuarioOpcion> currenUsuarioOpcion = usuarioOpcionRepository.findById(llaveCompuesta);
        if (currenUsuarioOpcion.isPresent()) {
            usuarioOpcionRepository.delete(currenUsuarioOpcion.get());
            response.setCode(200);
            response.setMessage("Registro Eliminado");

        } else {
            response.setCode(400);
            response.setMessage("No existe el Registro");
        }
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
