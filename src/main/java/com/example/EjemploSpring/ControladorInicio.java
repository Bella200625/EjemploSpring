package com.example.EjemploSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ControladorInicio {

    @GetMapping("/")
    public String inicio() {
        log.info("Ejecutando el controlador Inicio");
        return "Este es el inicio V2";
    }
}