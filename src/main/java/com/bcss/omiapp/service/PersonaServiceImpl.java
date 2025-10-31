package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Persona;
import com.bcss.omiapp.exception.PersonaRepetidaException;
import com.bcss.omiapp.exception.UsuarioNoEncontradoException;
import com.bcss.omiapp.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        if(personaRepository.existsPersonaByNumeroTelefono(persona.getNumeroTelefono())) throw new PersonaRepetidaException();
        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> findByTelefono(String telefono) {
        Optional<Persona> persona = personaRepository.findByTelefono(telefono);
        if(persona.isPresent()) return persona;
        else throw new UsuarioNoEncontradoException();
    }

    @Override
    public Optional<Persona> findById(Integer idPersona) {
        Optional<Persona> persona = personaRepository.findById(idPersona);
        if(persona.isPresent()) return persona;
        else throw new UsuarioNoEncontradoException();
    }

    @Override
    public Optional<Persona> findByNombre(String nombre) {
        Optional<Persona> persona = personaRepository.findByNombre(nombre);
        if(persona.isPresent()) return persona;
        else throw new UsuarioNoEncontradoException();
    }
}
