package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    Optional<Trabajador> findByEmail(String email);
    boolean existsByEmail(String email);
}
