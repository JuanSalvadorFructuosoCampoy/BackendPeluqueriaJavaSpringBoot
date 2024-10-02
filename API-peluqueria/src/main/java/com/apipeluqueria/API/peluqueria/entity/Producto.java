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
@Table(name = "productos")
public class Producto {

    @Id
    private String id;
    private String nombre;
    private Integer stock;
    private Double precio;
}
