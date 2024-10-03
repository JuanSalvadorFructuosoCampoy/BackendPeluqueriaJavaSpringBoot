package com.apipeluqueria.API.peluqueria.repository;

import com.apipeluqueria.API.peluqueria.entity.CierreCaja;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CierreCajaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CierreCaja> findCierreCajaByFecha(Date fecha) {
        String sql;
        if (fecha == null) {
            sql = "SELECT " +
                    "DATE(fecha) as fecha, " +
                    "SUM(CASE WHEN tipo = 'tarjeta' THEN total ELSE 0 END) as tarjeta, " +
                    "SUM(CASE WHEN tipo = 'efectivo' THEN total ELSE 0 END) as efectivo, " +
                    "SUM(total) as total " +
                    "FROM ventas " +
                    "GROUP BY DATE(fecha)";
        } else {
            sql = "SELECT DATE(fecha) as fecha, " +
                    "SUM(CASE WHEN tipo = 'tarjeta' THEN total ELSE 0 END) as tarjeta, " +
                    "SUM(CASE WHEN tipo = 'efectivo' THEN total ELSE 0 END) as efectivo, " +
                    "SUM(total) as total " +
                    "FROM ventas " +
                    "WHERE DATE(fecha) = :fecha " +
                    "GROUP BY DATE(fecha)";
        }
        Query query = entityManager.createNativeQuery(sql, "CierreCajaMapping");
        if (fecha != null) {
            query.setParameter("fecha", fecha);
        }
        return query.getResultList();
    }
}