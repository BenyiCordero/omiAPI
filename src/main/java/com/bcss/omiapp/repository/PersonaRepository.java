package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByNumeroTelefono(String telefono);
    boolean existsPersonaByNumeroTelefono(String numeroTelefono);
    Optional<Persona> findByNombre(String nombre);
}
