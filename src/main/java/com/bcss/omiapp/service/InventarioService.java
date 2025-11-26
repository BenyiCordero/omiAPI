package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.domain.InventarioDetails;
import com.bcss.omiapp.dto.response.InventarioListResponse;
import com.bcss.omiapp.dto.response.InventarioDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InventarioService {
    List<Inventario> getAll();
    Inventario save(Inventario inventario);
    Inventario getInventarioPerSucursal(Integer id);
    
    // Nuevos métodos con DTOs consistentes
    InventarioListResponse getAllList();
    InventarioDetailResponse getByIdDetail(Integer id);
}
