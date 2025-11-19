package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.domain.InventarioDetails;

import java.util.List;

public interface InventarioService {
    List<Inventario> getAll();
    Inventario save(Inventario inventario);
    Inventario getInventarioPerSucursal(Integer id);
}
