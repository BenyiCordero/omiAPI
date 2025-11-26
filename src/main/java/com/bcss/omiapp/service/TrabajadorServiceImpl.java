package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.exception.RepeatedException;
import com.bcss.omiapp.repository.TrabajadorRepository;
import com.bcss.omiapp.dto.response.TrabajadorListResponse;
import com.bcss.omiapp.dto.response.TrabajadorDetailResponse;
import com.bcss.omiapp.mappers.TrabajadorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, TrabajadorMapper trabajadorMapper) {
        this.trabajadorRepository = trabajadorRepository;
        this.trabajadorMapper = trabajadorMapper;
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        if (trabajadorRepository.existsByEmail(trabajador.getEmail())) throw new RepeatedException("Trabajador ya existente");
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public Optional<Trabajador> findByEmail(String email) {
        Optional<Trabajador> trabajador = trabajadorRepository.findByEmail(email);
        if(trabajador.isPresent()) return trabajador;
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public Optional<Trabajador> findById(Integer idTrabajador) {
        Optional<Trabajador> trabajador = trabajadorRepository.findById(idTrabajador);
        if (trabajador.isPresent()) return trabajador;
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public List<Trabajador> findAllTrabajadoresBySucursal(Integer id) {
        return null;
    }

    @Override
    public TrabajadorListResponse getAllList() {
        List<Trabajador> trabajadores = trabajadorRepository.findAll();
        if (trabajadores.isEmpty()) {
            throw new NotFoundException("No hay trabajadores para mostrar");
        }
        return new TrabajadorListResponse(trabajadores.stream()
            .map(trabajadorMapper::mapToBasic)
            .toList());
    }

    @Override
    public TrabajadorDetailResponse getByIdDetail(Integer id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Trabajador no encontrado"));
        return trabajadorMapper.mapToDetail(trabajador);
    }
}
