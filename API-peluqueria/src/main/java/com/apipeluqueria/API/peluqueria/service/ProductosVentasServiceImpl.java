package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.ProductosVentas;
import com.apipeluqueria.API.peluqueria.entity.Venta;
import com.apipeluqueria.API.peluqueria.exception.ProductosVentasNoEncontradoException;
import com.apipeluqueria.API.peluqueria.repository.ProductosVentasRepository;
import com.apipeluqueria.API.peluqueria.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosVentasServiceImpl implements ProductosVentasService{

    private final ProductosVentasRepository productosVentasRepository;
    private final VentaRepository ventaRepository;

    public ProductosVentasServiceImpl(ProductosVentasRepository productosVentasRepository, VentaRepository ventaRepository) {
        this.productosVentasRepository = productosVentasRepository;
        this. ventaRepository = ventaRepository;
    }

    @Override
    public List<ProductosVentas> listar() {
        return productosVentasRepository.findAll();
    }

    @Override
    public ProductosVentas save(ProductosVentas productosVentas) {
        Venta venta = ventaRepository.findById(productosVentas.getId()).get();
        productosVentas.setVenta(venta);
        return productosVentasRepository.save(productosVentas);
    }

    @Override
    public void eliminar(int id) throws ProductosVentasNoEncontradoException {
        productosVentasRepository.deleteById(id);
    }

    @Override
    public List<ProductosVentas> listarPorVenta(Integer id) {
        return productosVentasRepository.findByVenta(id);
    }
}
