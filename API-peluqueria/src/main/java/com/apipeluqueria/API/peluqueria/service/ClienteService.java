package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Cliente;
import com.apipeluqueria.API.peluqueria.exception.ClienteNoEncontradoException;


import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listar();

    Optional<Cliente> porId(int id) throws ClienteNoEncontradoException;

    Cliente save(Cliente empleado);

    void eliminar(int id) throws ClienteNoEncontradoException;

}
