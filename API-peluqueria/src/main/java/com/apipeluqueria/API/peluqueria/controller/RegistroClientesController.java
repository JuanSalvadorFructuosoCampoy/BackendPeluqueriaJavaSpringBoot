package com.apipeluqueria.API.peluqueria.controller;



import com.apipeluqueria.API.peluqueria.dto.RegistroClientesDTO;
import com.apipeluqueria.API.peluqueria.entity.RegistroClientes;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.RegistroClientesNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.RegistroClientesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroClientesController {

    private final RegistroClientesService registroClientesService;

    @Autowired
    public RegistroClientesController(RegistroClientesService registroClientesService) {
        this.registroClientesService = registroClientesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar registros de clientes", description = "Listar todos los registros de clientes")
    public List<RegistroClientes> listar(){
        return registroClientesService.listar();
    }

    @GetMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar registros por cliente", description = "Listar registros asociados a un cliente por su ID")
    public List<RegistroClientes> listarPorCliente(@PathVariable Integer clienteId) {
        List<RegistroClientes> registros = registroClientesService.listarPorCliente(clienteId);
        return registroClientesService.listarPorCliente(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear registro de cliente", description = "Crea un nuevo registro de cliente")
    public RegistroClientes crear(@RequestBody RegistroClientesDTO registroClientesDTO) throws EmpleadoNoEncontradoException {
        return registroClientesService.save(registroClientesDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualizar registro de cliente", description = "Actualiza un registro de cliente")
    public RegistroClientes editar(@RequestBody RegistroClientesDTO registroClientesDTO, @PathVariable Integer id) throws RegistroClientesNoEncontradoException, EmpleadoNoEncontradoException {
        if (registroClientesService.porId(id).isEmpty()) {
            throw new RegistroClientesNoEncontradoException("No existe el registro con id: " + id);
        } else {
            registroClientesDTO.setId(id);
            return registroClientesService.save(registroClientesDTO);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar registro de cliente", description = "Elimina un registro de cliente")
    public void eliminar(@PathVariable Integer id) throws RegistroClientesNoEncontradoException {
        registroClientesService.eliminar(id);
    }



}

