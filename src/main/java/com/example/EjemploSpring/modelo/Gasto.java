package com.example.EjemploSpring.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity // Le dice a Spring que esta clase es una Entidad de Base de Datos
@Table(name = "gastos") // Mapea exactamente con el nombre de tu tabla en MySQL
public class Gasto {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT en MySQL
    private Integer id;

    private String fecha;
    private String lugar;
    private String descripcion;

    @Column(name = "valor_total_sin_iva") // Mapea con el nombre exacto de la columna en phpMyAdmin
    private Double valorTotalSinIVA;

    @Column(name = "iva_total")
    private Double ivaTotal;

    @Column(name = "valor_total_con_iva")
    private Double valorTotalConIVA;

   @ManyToOne
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula")
    private Usuario usuario;

    public void calcularValorTotalConIva() {
        double sinIva = (this.valorTotalSinIVA != null) ? this.valorTotalSinIVA : 0.0;
        double iva = (this.ivaTotal != null) ? this.ivaTotal : 0.0;
        this.valorTotalConIVA = sinIva + iva;
    }

}