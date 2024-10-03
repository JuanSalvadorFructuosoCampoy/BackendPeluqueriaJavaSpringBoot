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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar un servicio por ID", description = "Edita un servicio por ID")
    public Servicio editar(@PathVariable String id, @RequestBody Servicio servicio) throws ServicioNoEncontradoException {
        if(servicioService.porId(id).isEmpty()){
            throw new ServicioNoEncontradoException("No existe el servicio con el id:" + id);
        }else{
            servicio.setId(id);
        }
        return servicioService.save(servicio);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un servicio por ID", description = "Elimina un servicio por ID")
    public void eliminar(@PathVariable String id) throws ServicioNoEncontradoException {
        if(servicioService.porId(id).isEmpty()){
            throw new ServicioNoEncontradoException("No existe el servicio con el id:" + id);
        }else{
            servicioService.eliminar(id);
        }
    }
}
