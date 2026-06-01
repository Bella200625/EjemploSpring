package com.example.EjemploSpring.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.EjemploSpring.modelo.Gasto;

public interface GastoCrud extends CrudRepository<Gasto, Integer> {
   
}