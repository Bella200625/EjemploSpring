package com.example.EjemploSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.EjemploSpring.modelo.Gasto;
import com.example.EjemploSpring.modelo.Usuario;
import com.example.EjemploSpring.servicio.IUsuarioServicio;
import com.example.EjemploSpring.servicio.IGastoServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private IGastoServicio gastoServicio;
    @Autowired
    private IUsuarioServicio userServicio;

    @GetMapping("/listar/{cedula}")
    public String listarGastos(@PathVariable("cedula") String cedula, Model model) {
    
    // Pasamos la cédula para que se pinte arriba en el HTML
    model.addAttribute("cedula", cedula);
    
    // Llamamos al filtro que hicimos en el paso anterior
    List<Gasto> listaFiltrada = gastoServicio.buscarGastosPorUsuario(cedula);
    model.addAttribute("gastos", listaFiltrada);
    
    return "lista_gastos"; 
}
    @GetMapping("/agregar/{cedula}")
    public String mostrarFormulario(@PathVariable("cedula") String cedula, Model model) {
        
        model.addAttribute("cedula", cedula);
        model.addAttribute("gasto", new Gasto()); // Objeto vacío que llenará el formulario
        
        return "formulario_gasto"; // Abre formulario_gasto.html
    }

    @PostMapping("/guardar")
    public String guardarGasto(@Valid Gasto gasto, Errors errores, 
                                @RequestParam("usuarioCedula") String usuarioCedula) {
        
        if (errores.hasErrors()) {
            return "formulario_gasto";
        }

        Usuario usuarioBuscado = new Usuario();
        usuarioBuscado.setCedula(usuarioCedula);
        Usuario usuarioReal = userServicio.buscar(usuarioBuscado);
        
        gasto.setUsuario(usuarioReal);
        gasto.calcularValorTotalConIva();
        gastoServicio.guardarGasto(gasto); 
        
        return "redirect:/gastos/listar/" + usuarioCedula;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarGasto(Gasto gasto) {
        // Guardamos la cédula antes de borrar el gasto para saber a dónde redirigir después
        gasto = gastoServicio.buscarGasto(gasto);
        String cedulaUsuario = gasto.getUsuario().getCedula();
        
        // Ejecutamos el borrado físico en la BD
        gastoServicio.eliminarGasto(gasto);
        
        // Redirigimos a la tabla de gastos de ese usuario específico
        return "redirect:/gastos/listar/" + cedulaUsuario;
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditarGasto(Gasto gasto, Model model) {
        // Buscamos el gasto real con todos sus datos en la BD
        gasto = gastoServicio.buscarGasto(gasto);
        
        // Mandamos el gasto lleno al formulario y la cédula deL usuario
        model.addAttribute("gasto", gasto);
        model.addAttribute("cedula", gasto.getUsuario().getCedula());
        
        // Reutilizamos formulario_gasto.html 
        return "formulario_gasto";
    }

}