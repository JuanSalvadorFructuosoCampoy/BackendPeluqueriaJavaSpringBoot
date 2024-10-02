package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
    Empleado findByNombre(String nombre);
    Empleado findByNombreAndPassword(String nombre, String password);
}
