package com.example.EjemploSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Ejecutando el controlador Spring MVC");
        
        
        String mensaje = "Hola Mundo con Thymeleaf";
        model.addAttribute("mensaje", mensaje);
        
        // Retorna la vista llamada "index.html"
        return "index";
    }
}