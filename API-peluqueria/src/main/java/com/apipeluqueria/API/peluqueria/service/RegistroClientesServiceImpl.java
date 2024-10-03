package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.dto.RegistroClientesDTO;
import com.apipeluqueria.API.peluqueria.entity.Cliente;
import com.apipeluqueria.API.peluqueria.entity.RegistroClientes;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.RegistroClientesNoEncontradoException;
import com.apipeluqueria.API.peluqueria.repository.ClienteRepository;
import com.apipeluqueria.API.peluqueria.repository.RegistroClientesRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroClientesServiceImpl implements RegistroClientesService{

    private final RegistroClientesRepository registroClientesRepository;
    private final ClienteRepository clienteRepository;

    public RegistroClientesServiceImpl(RegistroClientesRepository registroClientesRepository, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.registroClientesRepository = registroClientesRepository;
    }

    @Override
    public List<RegistroClientes> listar() {
        return registroClientesRepository.findAll();
    }

    @Override
    public Optional<RegistroClientes> porId(int id) throws RegistroClientesNoEncontradoException {
        if(registroClientesRepository.findById(id) == null)
        {
            throw new RegistroClientesNoEncontradoException("No existe el cliente con id: " + id);
        }
        return registroClientesRepository.findById(id);
    }

    @Override
    public RegistroClientes save(RegistroClientesDTO registroClientesDTO) throws EmpleadoNoEncontradoException {

        RegistroClientes registroClientes = new RegistroClientes();
        System.out.println("REGISTRO CLIENTES DTO: " + registroClientesDTO);
        if(registroClientesDTO.getId() != null){
            RegistroClientes registroClientesDb = registroClientesRepository.findById(registroClientesDTO.getId()).get();
            if(registroClientesDTO.getEvento() == null){
                registroClientesDTO.setEvento(registroClientesDb.getEvento());
            }
            if(registroClientesDTO.getIdCliente() == null){
                registroClientesDTO.setIdCliente(registroClientesDb.getCliente().getId());
            }
            if(registroClientesDTO.getFecha() == null){
                registroClientesDTO.setFecha(registroClientesDb.getFecha());
            }
        }

        registroClientes.setId(registroClientesDTO.getId());
        if(registroClientesDTO.getFecha() == null){
            registroClientes.setFecha(new Date());
        }else{
            registroClientes.setFecha(registroClientesDTO.getFecha());
        }
        registroClientes.setEvento(registroClientesDTO.getEvento());

        Cliente cliente = clienteRepository.getById(registroClientesDTO.getIdCliente());
        if(cliente == null){
            throw new EmpleadoNoEncontradoException("No existe el cliente con id: " + registroClientesDTO.getIdCliente());
        }
        registroClientes.setCliente(cliente);
        return registroClientesRepository.save(registroClientes);
    }

    @Override
    public void eliminar(int id) throws RegistroClientesNoEncontradoException {
        if(registroClientesRepository.findById(id) == null){
            throw new RegistroClientesNoEncontradoException("No existe el cliente con id: " + id);
        }
        registroClientesRepository.deleteById(id);
    }

    @Override
    public List<RegistroClientes> listarPorCliente(Integer clienteId) {
        return registroClientesRepository.findByClienteId(clienteId);
    }
}
