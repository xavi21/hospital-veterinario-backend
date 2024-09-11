package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Empleado;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.EmpleadoProjection;
import com.paraisocanino.hospital_veterinario.repository.EmpleadoRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getEmpleado(@RequestHeader("Authorization") String tokenAdmin) {
        List<EmpleadoProjection> empleados = empleadoRepository.findAllEmpleado();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(empleados);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postPersona(@RequestBody Empleado empleado, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        empleado.setFechamodificacion(dateNow());
        empleado.setUsuariomodificacion(user);
        empleado.setUsuariocreacion(user);
        empleado.setFechacreacion(dateNow());

        empleadoRepository.save(empleado);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(empleado);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse updateEmpleado(@RequestBody Empleado empleado, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        Optional<Empleado> currentEmpleado = empleadoRepository.findById(empleado.getIdempleado());

        if (currentEmpleado.isPresent()) {

            empleado.setFechamodificacion(dateNow());
            empleado.setUsuariomodificacion(user);
            empleado.setUsuariocreacion(currentEmpleado.get().getUsuariocreacion());
            empleado.setFechacreacion(currentEmpleado.get().getFechacreacion());
            empleadoRepository.save(empleado);

            response.setCode(200);
            response.setMessage("Empleado Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }

        response.setData(empleado);
        return response;
    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse deletePersona(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Empleado> currentEmpleado = empleadoRepository.findById(id);

        if (currentEmpleado.isPresent()) {
            empleadoRepository.delete(currentEmpleado.get());
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
