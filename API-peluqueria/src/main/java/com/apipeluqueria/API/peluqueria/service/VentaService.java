package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.dto.CierreCajaDTO;
import com.apipeluqueria.API.peluqueria.entity.Venta;
import com.apipeluqueria.API.peluqueria.exception.VentaNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> listar();

    Optional<Venta> porId(Integer id) throws VentaNoEncontradoException;

    Venta save(Venta venta);

    void eliminar(Integer id) throws VentaNoEncontradoException;

    List<CierreCajaDTO> findVentasAgrupadasPorFecha();
}
