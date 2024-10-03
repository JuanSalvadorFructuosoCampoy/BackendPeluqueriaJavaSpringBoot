package com.apipeluqueria.API.peluqueria.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ProductosVentasId implements Serializable {
    private Integer id;
    private String idItem;
}
