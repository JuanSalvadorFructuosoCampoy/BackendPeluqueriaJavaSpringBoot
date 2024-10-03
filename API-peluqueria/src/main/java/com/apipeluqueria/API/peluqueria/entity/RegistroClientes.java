package com.apipeluqueria.API.peluqueria.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "registro_clientes")
public class RegistroClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;
    @Column(length = 1000)
    private String evento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private Cliente cliente;

    //Usamos el toString de forma manual para evitar un StackOverFlowError al hacer un POST de un registro de cliente con un cliente.
    @Override
    public String toString() {
        return "RegistroClientes{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", evento='" + evento + '\'' +
                '}';
    }
}
