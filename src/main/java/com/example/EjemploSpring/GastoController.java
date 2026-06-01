package com.example.EjemploSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.EjemploSpring.modelo.Gasto;
import com.example.EjemploSpring.servicio.IGastoServicio;

@Controller
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private IGastoServicio gastoServicio;

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
    public String guardarGasto(@ModelAttribute("gasto") Gasto gasto, 
                                    @RequestParam("usuarioCedula") String usuarioCedula) {
        
        // Amarramos el gasto a la cédula del usuario (Llave Foránea)
        com.example.EjemploSpring.modelo.Usuario usuario = new com.example.EjemploSpring.modelo.Usuario();
        usuario.setCedula(usuarioCedula);
        gasto.setUsuario(usuario);
        
        gasto.calcularValorTotalConIva();
        
        gastoServicio.guardarGasto(gasto); 
        
        // Redirige a la tabla para ver el nuevo gasto registrado
        return "redirect:/gastos/listar/" + usuarioCedula;
    }

}