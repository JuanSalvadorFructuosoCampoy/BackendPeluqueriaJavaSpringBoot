package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.entity.RegistroClientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroClientesRepository extends JpaRepository<RegistroClientes, Integer> {
    List<RegistroClientes> findByClienteId(Integer clienteId);
}
