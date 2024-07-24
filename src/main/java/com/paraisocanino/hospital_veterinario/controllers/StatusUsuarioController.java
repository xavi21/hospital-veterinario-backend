package com.paraisocanino.hospital_veterinario.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/statususuario")
public class StatusUsuarioController {
    @GetMapping("/all")
    public String usuarios() {
        return "Public Content.";
    }
}
