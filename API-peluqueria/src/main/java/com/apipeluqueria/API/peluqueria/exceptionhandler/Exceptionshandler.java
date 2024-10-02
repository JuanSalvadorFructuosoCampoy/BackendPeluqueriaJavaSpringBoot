package com.apipeluqueria.API.peluqueria.exceptionhandler;

import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class Exceptionshandler {

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity handleEmpleadoNoEncontradoException(EmpleadoNoEncontradoException e){
        return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
    }
}
