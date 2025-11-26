package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Credito;
import com.bcss.omiapp.domain.EnumEstadoCredito;
import com.bcss.omiapp.dto.request.CreditoRequest;
import com.bcss.omiapp.dto.response.CreditoResponse;

import java.util.List;

public interface CreditoService {
    List<CreditoResponse> getAll();
    CreditoResponse getById(Integer id);
    Credito create(CreditoRequest request);
    Credito update(Integer id, CreditoRequest request);
    void delete(Integer id);
    List<CreditoResponse> getByCliente(Integer idCliente);
    CreditoResponse getByVenta(Integer idVenta);
    List<CreditoResponse> getByEstado(EnumEstadoCredito estado);
    Credito updateSaldo(Integer id, Double nuevoSaldo);
    Credito cancelar(Integer id);
    List<Credito> getMorosos();
}
