package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Cliente;
import com.bcss.omiapp.dto.request.ClienteRegisterRequest;
import com.bcss.omiapp.dto.request.ClienteUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente save(ClienteRegisterRequest clienteRegisterRequest);
    List<Cliente> findByName(String nombre);
    Optional<Cliente> findById(Integer idCliente);
    List<Cliente> findAllClientes();
    Cliente updateCliente(Integer id, ClienteUpdateRequest clienteUpdateRequest);
}
