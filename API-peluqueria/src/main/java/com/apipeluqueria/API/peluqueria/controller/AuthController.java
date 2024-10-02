package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.entity.Empleado;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.request.AuthenticationRequest;
import com.apipeluqueria.API.peluqueria.response.AuthenticationResponse;
import com.apipeluqueria.API.peluqueria.service.EmpleadoService;
import com.apipeluqueria.API.peluqueria.utility.JwtUtil;
import com.apipeluqueria.API.peluqueria.encoder.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/auth")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request) throws EmpleadoNoEncontradoException {
        log.info("createToken(-)");
        // Authenticate the user
        userDetailsService.loadUserByUsername(request.getNombre());
        Empleado empleado = empleadoService.findByNombre(request.getNombre());
        if(empleado == null){
            throw new EmpleadoNoEncontradoException("Credenciales incorrectas");
        }
        //Codificamos la contraseña introducia para compararla con la de la base de datos
        String encodedPassword = PasswordEncoder.encode(request.getPassword());

        if(!empleado.getPassword().equals(encodedPassword)){
            throw new EmpleadoNoEncontradoException("Credenciales incorrectas");
        }

        // Generate the token
        String jwtToken = jwtUtil.generateToken(request.getNombre());
        //Guardar el token
        empleado.setToken(jwtToken);
        empleadoService.save(empleado);
        return new AuthenticationResponse(jwtToken);
    }
}
