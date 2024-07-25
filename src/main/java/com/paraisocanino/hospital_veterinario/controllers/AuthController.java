package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.payload.request.LoginRequest;
import com.paraisocanino.hospital_veterinario.payload.response.JwtResponse;
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

}
