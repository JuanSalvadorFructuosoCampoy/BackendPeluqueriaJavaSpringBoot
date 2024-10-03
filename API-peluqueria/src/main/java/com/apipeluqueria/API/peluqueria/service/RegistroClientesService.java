package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.dto.RegistroClientesDTO;
import com.apipeluqueria.API.peluqueria.entity.RegistroClientes;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.RegistroClientesNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface RegistroClientesService {

    List<RegistroClientes> listar();

    Optional<RegistroClientes> porId(int id) throws RegistroClientesNoEncontradoException;

    RegistroClientes save(RegistroClientesDTO registroClientesDTO) throws EmpleadoNoEncontradoException;

    void eliminar(int id) throws RegistroClientesNoEncontradoException;

    List<RegistroClientes> listarPorCliente(Integer clienteId);
}
