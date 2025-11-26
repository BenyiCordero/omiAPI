package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.response.TrabajadorListResponse;
import com.bcss.omiapp.dto.response.TrabajadorDetailResponse;

import java.util.List;
import java.util.Optional;

public interface TrabajadorService {
    Trabajador save(Trabajador trabajador);
    Optional<Trabajador> findByEmail(String email);
    List<Trabajador> findAllTrabajadoresBySucursal(Integer id);
    Optional<Trabajador> findById(Integer idTrabajador);
    
    // Nuevos métodos con DTOs consistentes
    TrabajadorListResponse getAllList();
    TrabajadorDetailResponse getByIdDetail(Integer id);
}
