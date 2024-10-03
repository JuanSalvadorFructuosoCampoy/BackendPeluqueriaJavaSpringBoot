package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicioRepository extends JpaRepository<Servicio,String> {
    @Query(value = "SELECT MAX(id) FROM servicios", nativeQuery = true)
    String findMaxId();
}
