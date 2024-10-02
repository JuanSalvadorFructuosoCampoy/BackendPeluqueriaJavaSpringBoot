package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Producto;
import com.apipeluqueria.API.peluqueria.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> porId(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            String idNuevo = productoRepository.findMaxId();
            int nuevoIdNumerico = parseInt(idNuevo.substring(1)) + 1;
            String nuevoId = String.format("P%04d", nuevoIdNumerico);
            producto.setId(nuevoId);
        } else {
            producto.setId("P0001");
        }

       return productoRepository.save(producto);
    }

    @Override
    public void eliminar(String id) {
        productoRepository.deleteById(id);
    }
}
