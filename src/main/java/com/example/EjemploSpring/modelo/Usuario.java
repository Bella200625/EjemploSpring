package com.example.EjemploSpring.modelo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

private static final long serialVersionUID = 1L;

    @Id
    private String cedula;
    private String clave;
    private String nombre;
    private String email;
}