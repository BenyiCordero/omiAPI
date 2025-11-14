package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.domain.Trabajador;

import java.util.List;

public interface SucursalService {
    Sucursal save(Sucursal sucursal);
    Sucursal findById(Integer id);
    Sucursal findByName(String name);
    List<Sucursal> getAllSucursales();
    Sucursal updateSucursal(Sucursal sucursal);
    void deleteSucursalById(Integer id);
    List<Trabajador> findAllTrabajadoresBySucursal(Integer id);
}
