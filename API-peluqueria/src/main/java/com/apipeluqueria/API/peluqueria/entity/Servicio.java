package com.apipeluqueria.API.peluqueria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    private String id;
    private String nombre;
    private Double precio;
}
