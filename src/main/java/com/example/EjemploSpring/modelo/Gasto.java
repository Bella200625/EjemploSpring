package com.example.EjemploSpring.modelo;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity // esto lee dice a Spring que esta clase es una Entidad de Base de Datos
@Table(name = "gastos") // Mapea exactamente con el nombre de tu tabla en MySQL
public class Gasto {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT en MySQL
    private Integer id;

    @NotNull 
    private String fecha;
    @NotEmpty
    private String lugar;
    @NotEmpty
    private String descripcion;

    @NotNull
    @Column(name = "valor_total_sin_iva") // Mapea con el nombre exacto de la columna en phpMyAdmin
    @Min(value = 0, message = "El valor debe ser mayor a cero")
    private Double valorTotalSinIVA;

    @Column(name = "iva_total")
    @NotNull
    @Min(value = 0, message = "El IVA no puede ser negativo")
    private Double ivaTotal;

    @Column(name = "valor_total_con_iva")
    private Double valorTotalConIVA;

   @ManyToOne
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula")
    private Usuario usuario;

    public void calcularValorTotalConIva() {

        double sinIva = (this.valorTotalSinIVA != null) ? this.valorTotalSinIVA : 0.0;
    
        double tasaIva = 0.19; 
        
        this.ivaTotal = sinIva * tasaIva;
        
        this.valorTotalConIVA = sinIva + this.ivaTotal;
    }

    

}