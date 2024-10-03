package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.entity.ProductosVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosVentasRepository extends JpaRepository<ProductosVentas, Integer> {
    @Query(value = "SELECT * FROM productos_ventas WHERE id = ?1", nativeQuery = true)
    List<ProductosVentas> findByVenta(Integer id);
}
