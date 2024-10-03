package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.exception.ProductoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.ProductoService;
import com.apipeluqueria.API.peluqueria.entity.Producto;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar productos", description = "Lista todos los productos")
    public List<Producto> listar(){
        return productoService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un producto por ID", description = "Obtiene un producto por ID")
    public Producto porid(@PathVariable String id) throws ProductoNoEncontradoException {
        return productoService.porId(id).orElseThrow(() -> new ProductoNoEncontradoException("No existe el producto con el id:" + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un producto", description = "Crea un producto nuevo")
    public Producto crear(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar un producto por ID", description = "Edita un producto por ID")
    public Producto editar(@PathVariable String id, @RequestBody Producto producto) throws ProductoNoEncontradoException {
        if(productoService.porId(id).isEmpty()) {
            throw new ProductoNoEncontradoException("No existe el producto con id: " + id);
        }else{
            producto.setId(id);
            System.out.println(producto);
        }
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un producto por ID", description = "Elimina un producto por ID")
    public void eliminar(@PathVariable String id) throws ProductoNoEncontradoException {
        productoService.eliminar(id);
    }
}
