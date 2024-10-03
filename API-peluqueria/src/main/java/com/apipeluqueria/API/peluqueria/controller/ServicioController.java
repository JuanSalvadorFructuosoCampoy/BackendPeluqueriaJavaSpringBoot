package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.entity.Servicio;
import com.apipeluqueria.API.peluqueria.exception.ServicioNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.ServicioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {
    private final ServicioService servicioService;

    @Autowired
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar servicios", description = "Lista todos los servicios")
    public List<Servicio> listar(){
        return servicioService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un servicio por ID", description = "Obtiene un servicio por ID")
    public Servicio porId(String id) throws ServicioNoEncontradoException {
        return servicioService.porId(id).orElseThrow(() -> new ServicioNoEncontradoException("No existe el servicio con el id:" + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un servicio", description = "Crea un servicio nuevo")
    public Servicio crear(@RequestBody Servicio servicio){
        return servicioService.save(servicio);
    }
}
