package com.apipeluqueria.API.peluqueria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idFiscal;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Integer telefono;
    private String direccion;

    @OneToMany(mappedBy = "cliente",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<RegistroClientes> registroClientes;

}
