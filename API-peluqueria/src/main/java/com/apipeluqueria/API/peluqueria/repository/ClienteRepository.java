package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
