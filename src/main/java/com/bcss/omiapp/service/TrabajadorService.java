package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Trabajador;

import java.util.Optional;

public interface TrabajadorService {
    Trabajador save(Trabajador trabajador);
    Optional<Trabajador> findByEmail(String email);
    Optional<Trabajador> findById(Integer idTrabajador);
}
