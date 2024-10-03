package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Cliente;
import com.apipeluqueria.API.peluqueria.exception.ClienteNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> porId(int id) throws ClienteNoEncontradoException {
        if(clienteRepository.findById(id) == null)
        {
            throw new ClienteNoEncontradoException("No existe el cliente con id: " + id);
        }
        return clienteRepository.findById(id);    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(int id) throws ClienteNoEncontradoException {
        if(clienteRepository.findById(id) == null)
        {
            throw new ClienteNoEncontradoException("No existe el cliente con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
