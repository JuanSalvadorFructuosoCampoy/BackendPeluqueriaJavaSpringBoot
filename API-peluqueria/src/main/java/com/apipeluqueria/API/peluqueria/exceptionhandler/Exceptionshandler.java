package com.apipeluqueria.API.peluqueria.exceptionhandler;

import com.apipeluqueria.API.peluqueria.exception.AgendaNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.EmpleadoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.ProductoNoEncontradoException;
import com.apipeluqueria.API.peluqueria.exception.ServicioNoEncontradoException;
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

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity handleProductoNoEncontradoException(ProductoNoEncontradoException e){
        return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(ServicioNoEncontradoException.class)
    public ResponseEntity handleServicioNoEncontradoException(ServicioNoEncontradoException e){
        return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(AgendaNoEncontradoException.class)
    public ResponseEntity handleAgendaNoEncontradoException(AgendaNoEncontradoException e){
        return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
    }
}
