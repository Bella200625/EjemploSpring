package com.example.EjemploSpring.servicio;

import java.util.List;

import com.example.EjemploSpring.modelo.Gasto;

public interface IGastoServicio {
    public List<Gasto> listarGastos();
    List<Gasto> buscarGastosPorUsuario(String cedula);
    void guardarGasto(Gasto gasto);
}