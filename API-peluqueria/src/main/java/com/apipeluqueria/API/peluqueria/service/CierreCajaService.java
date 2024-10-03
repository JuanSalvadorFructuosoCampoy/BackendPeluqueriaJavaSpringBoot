package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.entity.CierreCaja;

import java.util.Date;
import java.util.List;

public interface CierreCajaService {
    List<CierreCaja> findCierreCajaByFecha(Date fecha);
}
