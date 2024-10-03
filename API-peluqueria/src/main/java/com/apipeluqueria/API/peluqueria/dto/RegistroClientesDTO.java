package com.apipeluqueria.API.peluqueria.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RegistroClientesDTO {
    private Integer id;
    private Integer idCliente;
    private Date fecha;
    private String evento;
}
