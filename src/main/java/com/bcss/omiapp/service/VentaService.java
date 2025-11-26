package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.EnumEstadoVenta;
import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.dto.request.VentaRequest;
import com.bcss.omiapp.dto.response.VentaResponse;
import com.bcss.omiapp.dto.response.VentaListResponse;
import com.bcss.omiapp.dto.response.VentaDetailResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VentaService {
    VentaListResponse getAllList(@RequestParam(required = false) EnumEstadoVenta estado);
    VentaDetailResponse getByIdDetail(Integer id);
    List<VentaResponse> getAll(@RequestParam(required = false) EnumEstadoVenta estado);
    VentaResponse getById(Integer id);
    Venta save(VentaRequest venta);
    Venta update(Integer id, VentaRequest request);
    void delete(Integer id);
    List<Venta> getBySucursal(Integer idSucursal);
    List<Venta> getByCliente(Integer idCliente);
    List<Venta> getByTrabajador(Integer idTrabajador);
    Venta changeStatus(Integer id, EnumEstadoVenta estado);
}
