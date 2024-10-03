package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.entity.Agenda;
import com.apipeluqueria.API.peluqueria.exception.AgendaNoEncontradoException;
import com.apipeluqueria.API.peluqueria.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar agenda", description = "Lista todos los eventos de la agenda")
    public List<Agenda> listar(){
        return agendaService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un evento por ID", description = "Obtiene un evento por ID")
    public Agenda porId(int id) throws AgendaNoEncontradoException {
        return agendaService.porId(id).orElseThrow(() -> new AgendaNoEncontradoException("No existe el evento con id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un evento", description = "Crea un evento nuevo")
    public Agenda crear(@RequestBody Agenda agenda){
        return agendaService.save(agenda);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar un evento por ID", description = "Edita un evento por ID")
    public Agenda editar(@PathVariable Integer id, @RequestBody Agenda agenda) throws AgendaNoEncontradoException {
        if(agendaService.porId(id).isEmpty()) {
            throw new AgendaNoEncontradoException("No existe el evento con id: " + id);
        }else{
            agenda.setId(id);
        }
        return agendaService.save(agenda);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un evento por ID", description = "Elimina un evento por ID")
    public void eliminar(@PathVariable Integer id) throws AgendaNoEncontradoException {
            agendaService.eliminar(id);
    }


}
