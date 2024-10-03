package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Empleado;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listar();

    Optional<Empleado> porId(int id) throws EmpleadoNoEncontradoException;

    Empleado save(Empleado empleado);

    void eliminar(int id) throws EmpleadoNoEncontradoException;

    Empleado findByNombre(String nombre) throws EmpleadoNoEncontradoException;
}
