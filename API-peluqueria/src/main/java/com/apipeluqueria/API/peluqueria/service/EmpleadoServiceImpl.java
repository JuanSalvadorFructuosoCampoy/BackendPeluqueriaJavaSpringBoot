package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.encoder.PasswordEncoder;
import com.apipeluqueria.API.peluqueria.entity.Empleado;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
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
    public Optional<Empleado> porId(int id) throws EmpleadoNoEncontradoException {
        if(empleadoRepository.findById(id) == null)
        {
            throw new EmpleadoNoEncontradoException("No existe el empleado con id: " + id);
        }
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        if(empleado.getId() != null){
            Empleado empleadoDatabase = empleadoRepository.findById(empleado.getId()).get();
            if(empleado.getPassword() == null) {
                empleado.setPassword(empleadoDatabase.getPassword());
            }else{
                empleado.setPassword(PasswordEncoder.encode(empleado.getPassword()));
            }

            if(empleado.getActivo() == null){
                empleado.setActivo(empleadoDatabase.getActivo());
            }
            if(empleado.getToken() == null){
                empleado.setToken(empleadoDatabase.getToken());
            }
            if(empleado.getRol() == null){
                empleado.setRol(empleadoDatabase.getRol());
            }

            empleado.setToken(empleadoDatabase.getToken());
            empleado.setId(empleadoDatabase.getId());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(int id)  throws EmpleadoNoEncontradoException{
        if(empleadoRepository.findById(id) == null)
        {
            throw new EmpleadoNoEncontradoException("No existe el empleado con id: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado findByNombre(String nombre) throws EmpleadoNoEncontradoException {
        if(empleadoRepository.findByNombre(nombre) == null)
        {
            throw new EmpleadoNoEncontradoException("No existe el empleado con nombre: " + nombre);
        }
        return empleadoRepository.findByNombre(nombre);
    }

}
