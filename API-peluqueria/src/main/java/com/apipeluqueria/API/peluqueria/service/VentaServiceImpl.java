package com.apipeluqueria.API.peluqueria.service;

import com.apipeluqueria.API.peluqueria.dto.CierreCajaDTO;
import com.apipeluqueria.API.peluqueria.entity.Venta;
import com.apipeluqueria.API.peluqueria.exception.VentaNoEncontradoException;
import com.apipeluqueria.API.peluqueria.repository.VentaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService{

    @PersistenceContext
    private EntityManager entityManager;

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> porId(Integer id) throws VentaNoEncontradoException {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Integer id) throws VentaNoEncontradoException {
        ventaRepository.deleteById(id);
    }

    @Override
    public List<CierreCajaDTO> findVentasAgrupadasPorFecha() {
        String sql = "SELECT DATE(v.fecha) as fecha, " +
                "SUM(CASE WHEN v.tipo = 'tarjeta' THEN v.total ELSE 0 END) as totalTarjeta, " +
                "SUM(CASE WHEN v.tipo = 'efectivo' THEN v.total ELSE 0 END) as totalEfectivo, " +
                "SUM(v.total) as total " +
                "FROM ventas v " +
                "GROUP BY DATE(v.fecha)";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        List<CierreCajaDTO> cierreCajaDTOs = new ArrayList<>();

        for (Object[] result : results) {
            Date fecha = (Date) result[0];
            float totalTarjeta = ((Number) result[1]).floatValue();
            float totalEfectivo = ((Number) result[2]).floatValue();
            float total = ((Number) result[3]).floatValue();

            CierreCajaDTO dto = new CierreCajaDTO(fecha, totalTarjeta, totalEfectivo, total);
            cierreCajaDTOs.add(dto);
        }

        return cierreCajaDTOs;
    }
}
