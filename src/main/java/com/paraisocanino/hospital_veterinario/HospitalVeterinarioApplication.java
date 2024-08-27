package com.paraisocanino.hospital_veterinario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class HospitalVeterinarioApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HospitalVeterinarioApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HospitalVeterinarioApplication.class, args);
    }

}
