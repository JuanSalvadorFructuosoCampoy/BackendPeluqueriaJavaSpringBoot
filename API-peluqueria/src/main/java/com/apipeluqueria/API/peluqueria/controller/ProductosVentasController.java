package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.entity.ProductosVentas;
import com.apipeluqueria.API.peluqueria.service.ProductosVentasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos-ventas")
public class ProductosVentasController {

    private final ProductosVentasService productosVentasService;

    @Autowired
    public ProductosVentasController(ProductosVentasService productosVentasService) {
        this.productosVentasService = productosVentasService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar productos de ventas", description = "Listar todos los productos de ventas")
    public List<ProductosVentas> listar(){
        return productosVentasService.listar();
    }

    @GetMapping("/{ventaId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar productos por venta", description = "Listar productos asociados a una venta por su ID")
    public List<ProductosVentas> listarPorVenta(@PathVariable Integer ventaId) {
        List<ProductosVentas> productos = productosVentasService.listarPorVenta(ventaId);
        return productosVentasService.listarPorVenta(ventaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear producto de venta", description = "Crear un producto de venta")
    public ProductosVentas crear(@RequestBody ProductosVentas productosVentas){
        System.out.println("PRODUCTO DE LA VENTA: " + productosVentas);
        return productosVentasService.save(productosVentas);
    }
}
