package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Cliente;
import com.bcss.omiapp.dto.request.ClienteRegisterRequest;
import com.bcss.omiapp.dto.request.ClienteUpdateRequest;
import com.bcss.omiapp.dto.response.ClienteListResponse;
import com.bcss.omiapp.dto.response.ClienteDetailResponse;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente save(ClienteRegisterRequest clienteRegisterRequest);
    List<Cliente> findByName(String nombre);
    Optional<Cliente> findById(Integer idCliente);
    List<Cliente> findAllClientes();
    Cliente updateCliente(Integer id, ClienteUpdateRequest clienteUpdateRequest);
    
    // Nuevos métodos con DTOs consistentes
    ClienteListResponse getAllList();
    ClienteDetailResponse getByIdDetail(Integer id);
}
