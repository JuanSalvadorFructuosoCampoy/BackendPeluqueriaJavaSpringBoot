package com.apipeluqueria.API.peluqueria.entity;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@SqlResultSetMapping(
        name = "CierreCajaMapping",
        classes = @ConstructorResult(
                targetClass = CierreCaja.class,
                columns = {
                        @ColumnResult(name = "fecha", type = Date.class),
                        @ColumnResult(name = "tarjeta", type = Float.class),
                        @ColumnResult(name = "efectivo", type = Float.class),
                        @ColumnResult(name = "total", type = Float.class)
                }
        )
)
public class CierreCaja {
    private Date fecha;
    private float tarjeta;
    private float efectivo;
    private float total;
}
