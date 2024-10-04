package com.apipeluqueria.API.peluqueria.entity;

import com.apipeluqueria.API.peluqueria.dto.CierreCajaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "CierreCajaDTOMapping",
        classes = @ConstructorResult(
                targetClass = CierreCajaDTO.class,
                columns = {
                        @ColumnResult(name = "fecha", type = Date.class),
                        @ColumnResult(name = "totalTarjeta", type = Float.class),
                        @ColumnResult(name = "totalEfectivo", type = Float.class),
                        @ColumnResult(name = "total", type = Float.class)
                }
        )
)
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;
    private float total;

    private String cliente;

    @Column(name = "nombrecliente")
    private String nombreCliente;

    @Column(name = "direccioncliente")
    private String direccionCliente;

    @Column(name = "telefonocliente")
    private Integer telefonoCliente;

    @Column(name = "idfiscalcliente")
    private String idFiscalCliente;

    @Column(name = "nombreempleado")
    private String nombreEmpleado;

    private String tipo;
    private Integer iva;

    @OneToMany(mappedBy = "id",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<ProductosVentas> productosVentas;
}
