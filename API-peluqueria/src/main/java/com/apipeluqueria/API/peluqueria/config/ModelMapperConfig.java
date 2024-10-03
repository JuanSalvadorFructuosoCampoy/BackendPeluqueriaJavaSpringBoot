package com.apipeluqueria.API.peluqueria.config;

import com.apipeluqueria.API.peluqueria.dto.RegistroClientesDTO;
import com.apipeluqueria.API.peluqueria.entity.RegistroClientes;
import com.apipeluqueria.API.peluqueria.repository.RegistroClientesRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class ModelMapperConfig {

    private RegistroClientesRepository registroClientesRepository;

    @Autowired
    public ModelMapperConfig(RegistroClientesRepository registroClientesRepository) {
        this.registroClientesRepository = registroClientesRepository;
    }


    public ModelMapper registroClientesModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<RegistroClientes, RegistroClientesDTO> typeMap = modelMapper.createTypeMap(RegistroClientes.class, RegistroClientesDTO.class);
        typeMap.addMapping(src -> src.getCliente().getId(), RegistroClientesDTO::setIdCliente);
        typeMap.addMapping(RegistroClientes::getId, RegistroClientesDTO::setId);
        typeMap.addMapping(RegistroClientes::getFecha, RegistroClientesDTO::setFecha);
        typeMap.addMapping(RegistroClientes::getEvento, RegistroClientesDTO::setEvento);
        return modelMapper;
    }

    }
