package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.InventarioDetails;
import com.bcss.omiapp.dto.request.InventarioDetailsRequest;
import com.bcss.omiapp.dto.response.InventarioDetailsListResponse;
import com.bcss.omiapp.dto.response.InventarioDetailsDetailResponse;

import java.util.List;

public interface InventarioDetailsService {
    List<InventarioDetails> getAll();
    List<InventarioDetails> getAllDisponibles();
    List<InventarioDetails> getAllNoDisponibles();
    InventarioDetails getById(Integer id);
    InventarioDetails save(InventarioDetailsRequest inventarioDetails);
    InventarioDetails update(Integer id, InventarioDetailsRequest inventarioDetails);
    void delete(Integer id);
    List<InventarioDetails> getByInventario(Integer idInventario);
    List<InventarioDetails> getByProducto(Integer idProducto);
    
    // Nuevos métodos con DTOs consistentes
    InventarioDetailsListResponse getAllList();
    InventarioDetailsDetailResponse getByIdDetail(Integer id);
}
