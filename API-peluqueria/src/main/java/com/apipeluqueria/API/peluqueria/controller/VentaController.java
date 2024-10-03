package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.entity.Venta;
import com.apipeluqueria.API.peluqueria.exception.VentaNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar ventas", description = "Lista todas las ventas")
    public List<Venta> listar(){
        return ventaService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener una venta por ID", description = "Obtiene una venta por ID")
    public Venta porId(@PathVariable Integer id) throws VentaNoEncontradoException {
        return ventaService.porId(id).orElseThrow(() -> new VentaNoEncontradoException("No existe la venta con el id:" + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una venta", description = "Crea una venta nueva")
    public Venta crear(@RequestBody Venta venta){
        return ventaService.save(venta);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar una venta por ID", description = "Edita una venta por ID")
    public Venta editar(@PathVariable Integer id, @RequestBody Venta venta) throws VentaNoEncontradoException {
        if(ventaService.porId(id).isEmpty()) {
            throw new VentaNoEncontradoException("No existe la venta con id: " + id);
        }else{
            venta.setId(id);
        }
        return ventaService.save(venta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar una venta por ID", description = "Elimina una venta por ID")
    public void eliminar(@PathVariable Integer id) throws VentaNoEncontradoException {
        ventaService.eliminar(id);
    }

}
