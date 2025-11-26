package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.response.TrabajadorBasicResponse;
import com.bcss.omiapp.exception.EmptyObject;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.exception.RepeatedException;
import com.bcss.omiapp.repository.SucursalRepository;
import com.bcss.omiapp.dto.response.SucursalListResponse;
import com.bcss.omiapp.dto.response.SucursalDetailResponse;
import com.bcss.omiapp.mappers.SucursalMapper;
import com.bcss.omiapp.mappers.TrabajadorMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;
    private final TrabajadorMapper trabajadorMapper;

    public SucursalServiceImpl(SucursalRepository sucursalRepository,
                              SucursalMapper sucursalMapper,
                              TrabajadorMapper trabajadorMapper) {
        this.sucursalRepository = sucursalRepository;
        this.sucursalMapper = sucursalMapper;
        this.trabajadorMapper = trabajadorMapper;
    }


    @Override
    public Sucursal save(Sucursal sucursal) {
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
        Optional<Sucursal> sucursal = sucursalRepository.findByNombre(name);
        if(sucursal.isPresent()) return sucursal.get();
        else throw new NotFoundException(("Sucursal no encontrada"));
    }

    @Override
    public List<Sucursal> getAllSucursales() {
        List<Sucursal> sucursals = sucursalRepository.findAll();
        if (!sucursals.isEmpty()) return sucursals;
        else throw new EmptyObject("No hay sucursales para mostrar");
    }

    @Override
    public Sucursal updateSucursal(Sucursal sucursal) {
        Sucursal sucursalToReplace = sucursalRepository.findById(sucursal.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        BeanUtils.copyProperties(sucursal, sucursalToReplace, "id");
        return sucursalRepository.save(sucursalToReplace);
    }


    @Override
    public void deleteSucursalById(Integer id) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if (sucursal.isPresent()) {
            Sucursal existingSucursal = sucursal.get();
            existingSucursal.setActivo(false);
            sucursalRepository.save(existingSucursal);
            return;
        }
        throw new NotFoundException("Sucursal no encontrada");
    }

    @Override
    public List<Sucursal> getAllActive() {
        List<Sucursal> sucursales = sucursalRepository.findByActivo(true);
        if (!sucursales.isEmpty()) return sucursales;
        else throw new EmptyObject("No hay sucursales para mostrar");
    }

    @Override
    public List<Sucursal> getAllInactive() {
        List<Sucursal> sucursales = sucursalRepository.findByActivo(false);
        if (!sucursales.isEmpty()) return sucursales;
        else throw new EmptyObject("No hay sucursales para mostrar");
    }

    @Override
    public SucursalListResponse getAllList() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        if (sucursales.isEmpty()) {
            throw new EmptyObject("No hay sucursales para mostrar");
        }
        return new SucursalListResponse(sucursales.stream()
            .map(sucursalMapper::mapToBasic)
            .toList());
    }

    @Override
    public SucursalDetailResponse getByIdDetail(Integer id) {

        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        // Evita NPE cuando la lista viene null
        List<Trabajador> trabajadores = sucursal.getTrabajadores();

        List<TrabajadorBasicResponse> trabajadoresBasic =
                trabajadores == null
                        ? List.of()
                        : trabajadores.stream()
                        .map(trabajadorMapper::mapToBasic)
                        .toList();

        return new SucursalDetailResponse(
                sucursal.getIdSucursal(),
                sucursal.getNombre(),
                sucursal.getSucursalKey(),
                sucursal.getActivo(),
                trabajadoresBasic
        );
    }

}
