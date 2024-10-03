package com.apipeluqueria.API.peluqueria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;
    private float total;

    private String cliente;

    @Column(name = "nombrecliente")
    private String nombreCliente;

    @Column(name = "direccioncliente")
    private String direccionCliente;

    @Column(name = "telefonocliente")
    private Integer telefonoCliente;

    @Column(name = "idfiscalcliente")
    private String idFiscalCliente;

    @Column(name = "nombreempleado")
    private String nombreEmpleado;

    private String tipo;
    private Integer iva;
}
