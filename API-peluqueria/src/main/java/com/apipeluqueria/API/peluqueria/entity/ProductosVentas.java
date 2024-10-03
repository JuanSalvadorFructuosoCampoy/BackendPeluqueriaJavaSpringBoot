package com.apipeluqueria.API.peluqueria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "productos_ventas")
@IdClass(ProductosVentasId.class)
public class ProductosVentas {
    @Id
    private Integer id;

    @Id
    private String idItem;

    private String nombre;
    private Integer idCliente;
    private Integer cantidad;
    private float precio;

}
