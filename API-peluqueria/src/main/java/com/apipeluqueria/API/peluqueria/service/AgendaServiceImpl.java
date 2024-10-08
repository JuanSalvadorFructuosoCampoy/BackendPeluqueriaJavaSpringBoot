package com.apipeluqueria.API.peluqueria.service;


import com.apipeluqueria.API.peluqueria.entity.Agenda;
import com.apipeluqueria.API.peluqueria.exception.AgendaNoEncontradoException;
import com.apipeluqueria.API.peluqueria.repository.AgendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agenda> listar() {
        return agendaRepository.findAll();
    }

    @Override
    public Optional<Agenda> porId(Integer id) throws AgendaNoEncontradoException {
        if(agendaRepository.findById(id) == null){
            throw new AgendaNoEncontradoException("No existe la agenda con id: " + id);
        }
        return agendaRepository.findById(id);
    }

    @Override
    @Transactional
    public Agenda save(Agenda agenda) {
        if(agenda.getId() != null) {
            Agenda agendaDb = agendaRepository.findById(agenda.getId()).get();
            if(agenda.getFecha() == null){
                agenda.setFecha(agendaDb.getFecha());
            }
            if(agenda.getHora() == null){
                agenda.setHora(agendaDb.getHora());
            }
            if(agenda.getCita() == null){
                agenda.setCita(agendaDb.getCita());
            }
        } else {
            agenda.setFecha(new Date());
            agenda.setHora(new Time(System.currentTimeMillis()));
        }
        return agendaRepository.save(agenda);
    }

    @Override
    public void eliminar(Integer id)  throws AgendaNoEncontradoException{
        if(agendaRepository.findById(id) == null){
            throw new AgendaNoEncontradoException("No existe la agenda con id: " + id);
        }
        agendaRepository.deleteById(id);
    }
}
