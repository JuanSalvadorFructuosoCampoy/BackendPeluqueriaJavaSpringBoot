package com.apipeluqueria.API.peluqueria.service;


import com.apipeluqueria.API.peluqueria.entity.Empleado;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername(-)");
        Empleado empleado = empleadoRepository.findByNombre(username);
        if (empleado == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        boolean enabled = empleado.getActivo() == 1;

        return new org.springframework.security.core.userdetails.User(empleado.getNombre(), empleado.getPassword(), enabled, true, true, true, new ArrayList<>());
    }
}