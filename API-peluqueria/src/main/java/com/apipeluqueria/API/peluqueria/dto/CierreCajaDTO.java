package com.apipeluqueria.API.peluqueria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CierreCajaDTO {
    private Date fecha;
    private float tarjeta;
    private float efectivo;
    private float total;
}
