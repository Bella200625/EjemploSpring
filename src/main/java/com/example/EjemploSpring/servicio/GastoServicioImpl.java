package com.example.EjemploSpring.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EjemploSpring.dao.GastoCrud;
import com.example.EjemploSpring.modelo.Gasto;

@Service
public class GastoServicioImpl implements IGastoServicio {

    @Autowired
    private GastoCrud gastoCrud; // Tu repositorio que se queda VACÍO y limpio

    @Override
    public List<Gasto> listarGastos() {
        return (List<Gasto>) gastoCrud.findAll();
    }

    @Override
    public List<Gasto> buscarGastosPorUsuario(String cedula) {
        // Traemos TODOS los gastos de la base de datos usando el método por defecto
        List<Gasto> todosLosGastos = (List<Gasto>) gastoCrud.findAll();
        
        // Creamos una lista vacía para guardar solo los del usuario
        List<Gasto> gastosFiltrados = new ArrayList<>();
        
        // Recorremos y comparamos la cédula
        for (Gasto g : todosLosGastos) {
            // Si el gasto tiene un usuario y la cédula coincide con la de la URL...
            if (g.getUsuario() != null && g.getUsuario().getCedula().equals(cedula)) {
                gastosFiltrados.add(g); 
            }
        }
        
        return gastosFiltrados;
    }

    @Override
    public void guardarGasto(Gasto gasto) {
        gastoCrud.save(gasto); 
    }

    @Override
    public void eliminarGasto(Gasto gasto) {
        gastoCrud.delete(gasto); // El delete() de Spring borra por ID automáticamente
    }

    @Override
    public Gasto buscarGasto(Gasto gasto) {
        // Busca el gasto por ID, si no lo encuentra nos devuelve un valor nulo
        return gastoCrud.findById(gasto.getId()).orElse(null);
    }
}