package com.apipeluqueria.API.peluqueria.service;


import com.apipeluqueria.API.peluqueria.entity.ProductosVentas;
import com.apipeluqueria.API.peluqueria.exception.ProductosVentasNoEncontradoException;

import java.util.List;

public interface ProductosVentasService {

    List<ProductosVentas> listar();

    ProductosVentas save(ProductosVentas productosVentas);

    void eliminar(int id) throws ProductosVentasNoEncontradoException;

    List<ProductosVentas> listarPorVenta(Integer id);
}
