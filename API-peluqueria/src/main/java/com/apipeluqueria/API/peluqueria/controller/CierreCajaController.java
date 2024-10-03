package com.apipeluqueria.API.peluqueria.controller;

import com.apipeluqueria.API.peluqueria.entity.CierreCaja;
import com.apipeluqueria.API.peluqueria.service.CierreCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cierre-caja")
public class CierreCajaController {

    private final CierreCajaService cierreCajaService;

    @Autowired
    public CierreCajaController(CierreCajaService cierreCajaService) {
        this.cierreCajaService = cierreCajaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CierreCaja> getCierreCaja(@RequestParam(required = false) Date fecha) {
        return cierreCajaService.findCierreCajaByFecha(fecha);
    }
}