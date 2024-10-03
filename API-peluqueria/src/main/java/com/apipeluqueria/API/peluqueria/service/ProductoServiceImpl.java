package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Producto;
import com.apipeluqueria.API.peluqueria.exception.ProductoNoEncontradoException;
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
    public Optional<Producto> porId(String id) throws ProductoNoEncontradoException {
        if(productoRepository.findById(id) == null){
            throw new ProductoNoEncontradoException("No existe el producto con id: " + id);
        }
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        System.out.println(producto);
        System.out.println("El ID:" + producto.getId());
        if (producto.getId() == null) {
            String idNuevo = productoRepository.findMaxId();
            int nuevoIdNumerico = parseInt(idNuevo.substring(1)) + 1;
            String nuevoId = String.format("P%04d", nuevoIdNumerico);
            producto.setId(nuevoId);
        }
        if(productoRepository.findMaxId() == null){
            producto.setId("P0001");
        }

       return productoRepository.save(producto);
    }

    @Override
    public void eliminar(String id) throws ProductoNoEncontradoException {
        if(productoRepository.findById(id) == null){
            throw new ProductoNoEncontradoException("No existe el producto con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
