package com.example.EjemploSpring.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.EjemploSpring.modelo.Usuario;

public interface UsuarioCrud extends CrudRepository<Usuario, String> {
    
}