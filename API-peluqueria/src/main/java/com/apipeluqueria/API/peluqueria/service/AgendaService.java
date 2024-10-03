package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Agenda;
import com.apipeluqueria.API.peluqueria.exception.AgendaNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    List<Agenda> listar();

    Optional<Agenda> porId(Integer id) throws AgendaNoEncontradoException;

    Agenda save(Agenda agenda);

    void eliminar(Integer id) throws AgendaNoEncontradoException;
}
