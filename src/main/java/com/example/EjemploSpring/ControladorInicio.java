package com.example.EjemploSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.EjemploSpring.dao.UsuarioCrud;
import com.example.EjemploSpring.modelo.Usuario;
import com.example.EjemploSpring.servicio.IUsuarioServicio;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.mensaje}")    
    String dato;

    @Autowired
    UsuarioCrud crudUsuario;

    @Autowired
    IUsuarioServicio userServicio;


    @GetMapping("/")
    public String inicio(Model modelo) {
        String mensaje = "Saludos desde Spring MVC con paso de informacion";
        
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("dato", dato);
        
       List<Usuario> listaUsuarios = (List<Usuario>) userServicio.listarUsuarios();
        modelo.addAttribute("usuarios", listaUsuarios);

        log.info("Ejecutando el controlador inicio MVC");
        // Retorna la vista llamada "index.html"
        return "index";
    }
}