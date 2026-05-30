package com.example.EjemploSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.EjemploSpring.dao.UsuarioDao;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.mensaje}")    
    String dato;

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping("/")
    public String inicio(Model modelo) {
        String mensaje = "Saludos desde Spring MVC con paso de informacion";
        
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("dato", dato);
        
        var usuarios = usuarioDao.findAll();

        modelo.addAttribute("usuarios", usuarios);

        log.info("Ejecutando el controlador inicio MVC");
        // Retorna la vista llamada "index.html"
        return "index";
    }
}