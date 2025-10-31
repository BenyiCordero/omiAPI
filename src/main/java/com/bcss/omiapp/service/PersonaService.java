package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Persona;

import java.util.Optional;

public interface PersonaService {
    Persona save(Persona persona);
    Optional<Persona> findByTelefono(String telefono);
    Optional<Persona> findById(Integer idPersona);
    Optional<Persona> findByNombre(String nombre);
}
