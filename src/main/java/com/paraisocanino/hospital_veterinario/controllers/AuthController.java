package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Medicamento;
import com.paraisocanino.hospital_veterinario.models.Usuario;
import com.paraisocanino.hospital_veterinario.payload.request.LoginRequest;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.payload.response.JwtResponse;
import com.paraisocanino.hospital_veterinario.repository.ConsultaProjection;
import com.paraisocanino.hospital_veterinario.repository.MedicamentoProjection;
import com.paraisocanino.hospital_veterinario.repository.UsuarioProjection;
import com.paraisocanino.hospital_veterinario.repository.UsuarioRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import com.paraisocanino.hospital_veterinario.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getIdusuario(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getIdusuario(),
                userDetails.getUltimafechaingreso(),
                userDetails.getIntentosdeacceso(),
                userDetails.getSesionactual(),
                userDetails.getUltimafechacambiopassword(),
                userDetails.getTelefonomovil(),
                userDetails.getIdstatususuario(),
                userDetails.getIdsucursal()));
    }

    @GetMapping("/all")
    public GeneralResponseList getMedicamento(@RequestHeader("Authorization") String tokenAdmin) {
        List<UsuarioProjection> usuarios = usuarioRepository.findAllUsuarios();

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(usuarios);

        return response;
    }
}
