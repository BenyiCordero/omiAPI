package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.VentaDetails;
import com.bcss.omiapp.dto.request.VentaDetailsRequest;

import java.util.List;

public interface VentaDetailsService {
    List<VentaDetails> getAll();
    VentaDetails getById(Integer id);
    List<VentaDetails> getByVenta(Integer idVenta);
    VentaDetails save(VentaDetailsRequest ventaDetails);
    VentaDetails update(Integer id, VentaDetailsRequest ventaDetails);
    void delete(Integer id);
}
