package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.repository.ClienteRepository;
import com.bcss.omiapp.repository.InventarioRepository;
import com.bcss.omiapp.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repository;
    private final SucursalRepository sucursalRepository;

    public InventarioServiceImpl(InventarioRepository repository, SucursalRepository sucursalRepository) {
        this.repository = repository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<Inventario> getAll() {
        List<Inventario> inventarios = repository.findAll();
        if(inventarios.isEmpty()) throw new NotFoundException("No hay inventarios para mostrar");
        else return inventarios;
    }

    @Override
    public Inventario save(Inventario inventario) {
        Sucursal sucursal = sucursalRepository.findById(inventario.getSucursal().getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Inventario inventarioSaved = Inventario.builder()
                .titulo(inventario.getTitulo())
                .descripcion(inventario.getDescripcion())
                .fechaCreacion(LocalDate.now())
                .sucursal(sucursal)
                .build();
        return repository.save(inventarioSaved);
    }

    @Override
    public Inventario getInventarioPerSucursal(Integer id) {
        Optional<Inventario> inventario = repository.findBySucursal_idSucursal(id);
        if (inventario.isPresent()) return inventario.get();
        else throw new NotFoundException("Inventario con Sucursal ID: " + id + " no encontrado");
    }
}
