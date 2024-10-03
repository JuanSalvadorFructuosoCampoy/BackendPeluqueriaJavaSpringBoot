package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.CierreCaja;
import com.apipeluqueria.API.peluqueria.repository.CierreCajaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CierreCajaServiceImpl implements CierreCajaService {

    private final CierreCajaRepository cierreCajaRepository;

    public CierreCajaServiceImpl(CierreCajaRepository cierreCajaRepository) {
        this.cierreCajaRepository = cierreCajaRepository;
    }

    @Override
    public List<CierreCaja> findCierreCajaByFecha(Date fecha) {
        return cierreCajaRepository.findCierreCajaByFecha(fecha);
    }
}