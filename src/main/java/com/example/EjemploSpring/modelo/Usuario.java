package com.example.EjemploSpring.modelo;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{ 

private static final long serialVersionUID = 1L;

    @Id
    @NotEmpty
    private String cedula;
    
    @NotEmpty
    private String clave;
    
    @NotEmpty
    private String nombre;
    
    @Email
    @NotEmpty
    private String email;
}