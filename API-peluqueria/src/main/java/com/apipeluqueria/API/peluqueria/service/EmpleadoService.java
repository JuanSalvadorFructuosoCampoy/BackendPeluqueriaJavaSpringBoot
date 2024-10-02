package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listar();

    Optional<Empleado> porId(int id);

    Empleado save(Empleado empleado);

    void eliminar(int id);

    Empleado findByNombre(String nombre);
}
