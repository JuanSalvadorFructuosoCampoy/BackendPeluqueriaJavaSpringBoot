package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.encoder.PasswordEncoder;
import com.apipeluqueria.API.peluqueria.entity.Cliente;
import com.apipeluqueria.API.peluqueria.exception.ClienteNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar clientes", description = "Lista todos los clientes")
    public List<Cliente> listar(){
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un cliente por ID", description = "Obtiene un cliente por ID")
    public Cliente porid(int id) throws ClienteNoEncontradoException {
        return clienteService.porId(id).orElseThrow(() -> new ClienteNoEncontradoException("No existe el cliente con id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un cliente", description = "Crea un cliente nuevo")
    public Cliente crear(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar un cliente por ID", description = "Edita un cliente por ID")
    public Cliente editar(@PathVariable Integer id, @RequestBody Cliente cliente) throws ClienteNoEncontradoException {
        if(clienteService.porId(id).isEmpty()) {
            throw new ClienteNoEncontradoException("No existe el cliente con id: " + id);
        }
        cliente.setId(id);
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un cliente por ID", description = "Elimina un cliente por ID")
    public void eliminar(@PathVariable int id) throws ClienteNoEncontradoException {
        if(clienteService.porId(id).isEmpty()) {
            throw new ClienteNoEncontradoException("No existe el cliente con id: " + id);
        }else{
            clienteService.eliminar(id);
        }
        clienteService.eliminar(id);
    }
}
