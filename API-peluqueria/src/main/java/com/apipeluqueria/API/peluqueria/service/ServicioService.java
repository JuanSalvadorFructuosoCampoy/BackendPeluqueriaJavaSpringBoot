package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Servicio;
import com.apipeluqueria.API.peluqueria.exception.ServicioNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listar();

    Optional<Servicio> porId(String id) throws ServicioNoEncontradoException;

    Servicio save(Servicio servicio);

    void eliminar(String id) throws ServicioNoEncontradoException;
}
