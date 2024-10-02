package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.encoder.PasswordEncoder;
import com.apipeluqueria.API.peluqueria.entity.Empleado;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar empleados", description = "Lista todos los empleados")
    public List<Empleado> listar(){
        return empleadoService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un empleado por ID", description = "Obtiene un empleado por ID")
    public Empleado porid(int id) throws EmpleadoNoEncontradoException {
        return empleadoService.porId(id).orElseThrow(() -> new EmpleadoNoEncontradoException("No existe el empleado con id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un empleado", description = "Crea un empleado nuevo")
    public Empleado crear(@RequestBody Empleado empleado){
        empleado.setPassword(PasswordEncoder.encode(empleado.getPassword()));
        return empleadoService.save(empleado);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar un empleado por ID", description = "Edita un empleado por ID")
    public Empleado editar(@PathVariable Integer id, Empleado empleado) throws EmpleadoNoEncontradoException {
        if(empleadoService.porId(id).isEmpty()) {
            throw new EmpleadoNoEncontradoException("No existe el empleado con id: " + id);
        }else{
            empleado.setId(id);
            empleado.setPassword(PasswordEncoder.encode(empleado.getPassword()));
        }
        return empleadoService.save(empleado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un empleado por ID", description = "Elimina un empleado por ID")
    public void eliminar(@PathVariable int id){
        empleadoService.eliminar(id);
    }

    @GetMapping("/nombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un empleado por nombre", description = "Obtiene un empleado por nombre")
    public Empleado porNombre(String nombre) throws EmpleadoNoEncontradoException {
        return empleadoService.findByNombre(nombre);
    }
}
