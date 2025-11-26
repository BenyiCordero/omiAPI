package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.response.SucursalListResponse;
import com.bcss.omiapp.dto.response.SucursalDetailResponse;

import java.util.List;

public interface SucursalService {
    Sucursal save(Sucursal sucursal);
    Sucursal findById(Integer id);
    Sucursal findByName(String name);
    List<Sucursal> getAllSucursales();
    Sucursal updateSucursal(Sucursal sucursal);
    void deleteSucursalById(Integer id);
    List<Sucursal> getAllActive();
    List<Sucursal> getAllInactive();
    
    // Nuevos métodos con DTOs consistentes
    SucursalListResponse getAllList();
    SucursalDetailResponse getByIdDetail(Integer id);
}
