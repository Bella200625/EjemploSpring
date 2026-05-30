package com.example.EjemploSpring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.EjemploSpring.modelo.Usuario;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.mensaje}")    
    String dato;

    @GetMapping("/")
    public String inicio(Model modelo) {
        String mensaje = "Saludos desde Spring MVC con paso de informacion";
        
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("dato", dato);
        
        // primer usuariio
        Usuario u1 = new Usuario();
        u1.setCedula("1234");
        u1.setClave("Abcd");
        u1.setNombre("JOHN CARLOS ARRIETA ARRIETA");
        u1.setEmail("jarrieta@hotmail.com");
        
        modelo.addAttribute("alguien", u1);

        // Segundo Usuario
        Usuario u2 = new Usuario();
        u2.setCedula("777");
        u2.setClave("El mejor");
        u2.setNombre("JESUS DE NAZARET");
        u2.setEmail("jesuscristo@iglesia.com");
        
        // Tercer Usuario
        Usuario u3 = new Usuario();
        u3.setCedula("4321");
        u3.setClave("xyz");
        u3.setNombre("FULANITO DE TAL");
        u3.setEmail("fulanito@gmail.com");
        

        // Creación de la lista 
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(u2);
        usuarios.add(u3);

        modelo.addAttribute("usuarios", usuarios);

        log.info("Ejecutando el controlador inicio MVC");
        // Retorna la vista llamada "index.html"
        return "index";
    }
}