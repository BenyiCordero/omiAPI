package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.repository.ClienteRepository;
import com.bcss.omiapp.repository.InventarioRepository;

import java.util.List;
import java.util.Optional;

public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repository;

    public InventarioServiceImpl(InventarioRepository repository) { this.repository = repository; }

    @Override
    public List<Inventario> getAll() {
        List<Inventario> inventarios = repository.findAll();
        if(inventarios.isEmpty()) throw new NotFoundException("No hay inventarios para mostrar");
        else return inventarios;
    }

    @Override
    public Inventario save(Inventario inventario) {
        return repository.save(inventario);
    }

    @Override
    public Inventario getInventarioPerSucursal(Integer id) {
        Optional<Inventario> inventario = repository.findBySucursal(id);
        if (inventario.isPresent()) return inventario.get();
        else throw new NotFoundException("Inventario con Sucursal ID: " + id + " no encontrado");
    }
}
