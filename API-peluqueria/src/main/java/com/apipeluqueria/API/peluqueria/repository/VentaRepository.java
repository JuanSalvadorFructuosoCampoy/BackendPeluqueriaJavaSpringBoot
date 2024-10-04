package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.dto.CierreCajaDTO;
import com.apipeluqueria.API.peluqueria.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
