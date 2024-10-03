package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Servicio;
import com.apipeluqueria.API.peluqueria.repository.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService{

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listar() {
        return servicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> porId(String id) {
        return servicioRepository.findById(id);
    }

    @Override
    public Servicio save(Servicio servicio) {
        if(servicio.getId() == null){
            String idNuevo = servicioRepository.findMaxId();
            int nuevoIdNumerico = Integer.parseInt(idNuevo.substring(1)) + 1;
            String nuevoId = String.format("S%04d", nuevoIdNumerico);
            servicio.setId(nuevoId);
        }
        if(servicioRepository.findMaxId() == null){
            servicio.setId("S0001");
        }
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminar(String id) {
        servicioRepository.deleteById(id);
    }
}
