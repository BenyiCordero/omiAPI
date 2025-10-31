package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Persona;
import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.exception.PersonaRepetidaException;
import com.bcss.omiapp.exception.UsuarioNoEncontradoException;
import com.bcss.omiapp.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final PersonaService personaService;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, PersonaService personaService) {
        this.trabajadorRepository = trabajadorRepository;
        this.personaService = personaService;
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        personaService.save(trabajador.getPersona());
        if (trabajadorRepository.existsByEmail(trabajador.getEmail())) throw new PersonaRepetidaException();
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public Optional<Trabajador> findByEmail(String email) {
        Optional<Trabajador> trabajador = trabajadorRepository.findByEmail(email);
        if(trabajador.isPresent()) return trabajador;
        else throw new UsuarioNoEncontradoException();
    }

    @Override
    public Optional<Trabajador> findById(Integer idTrabajador) {
        Optional<Trabajador> trabajador = trabajadorRepository.findById(idTrabajador);
        if (trabajador.isPresent()) return trabajador;
        else throw new UsuarioNoEncontradoException();
    }
}
