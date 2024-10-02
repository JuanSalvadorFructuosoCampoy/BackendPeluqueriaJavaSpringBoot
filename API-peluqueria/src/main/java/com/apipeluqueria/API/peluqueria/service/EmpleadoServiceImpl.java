package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.Empleado;
import com.apipeluqueria.API.peluqueria.repository.EmpleadoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listar(){
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> porId(int id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(int id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado findByNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

}
