package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.exception.RepeatedException;
import com.bcss.omiapp.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }


    @Override
    public Sucursal save(Sucursal sucursal) {
        if(sucursalRepository.existsById(sucursal.getIdSucursal())) throw new RepeatedException("Sucursal ya existente");
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal findById(Integer id) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if(sucursal.isPresent()) return sucursal.get();
        else throw new NotFoundException("Sucursal no existente");
    }

    @Override
    public Sucursal findByName(String name) {
        return null;
    }

    @Override
    public List<Sucursal> getAllSucursales() {
        return List.of();
    }

    @Override
    public Sucursal updateSucursal(Sucursal sucursal) {
        return null;
    }

    @Override
    public void deleteSucursalById(Integer id) {

    }

    @Override
    public List<Trabajador> findAllTrabajadoresBySucursal(Integer id) {
        return List.of();
    }
}
