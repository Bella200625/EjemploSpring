package com.example.EjemploSpring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.mensaje}")    
    private String dato;

    @GetMapping("/")
    public String inicio(Model modelo) {
        String mensaje = "Saludos desde Spring MVC con paso de informacion";
        
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("dato", dato);
        
        log.info("Ejecutando el controlador inicio MVC");
        // Retorna la vista llamada "index.html"
        return "index";
    }
}