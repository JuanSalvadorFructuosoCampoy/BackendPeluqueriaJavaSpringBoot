package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Producto;
import com.apipeluqueria.API.peluqueria.exception.ProductoNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(String id) throws ProductoNoEncontradoException;

    Producto save(Producto producto);

    void eliminar(String id) throws ProductoNoEncontradoException;
}
