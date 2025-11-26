package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Credito;
import com.bcss.omiapp.dto.response.CreditoResponse;
import com.bcss.omiapp.dto.response.CreditoDetailResponse;
import com.bcss.omiapp.dto.response.ClienteBasicResponse;
import com.bcss.omiapp.dto.response.VentaBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper {
    
    private final ClienteMapper clienteMapper;
    private final VentaMapper ventaMapper;

    public CreditoMapper(ClienteMapper clienteMapper, VentaMapper ventaMapper) {
        this.clienteMapper = clienteMapper;
        this.ventaMapper = ventaMapper;
    }

    public CreditoResponse mapToResponse(Credito c) {
        ClienteBasicResponse clienteBasic = clienteMapper.mapToBasic(c.getCliente());
        VentaBasicResponse ventaBasic = c.getVenta() != null ? 
            ventaMapper.mapToBasic(c.getVenta()) : null;
            
        return new CreditoResponse(
                c.getIdCredito(),
                clienteBasic,
                ventaBasic,
                c.getMontoInicial(),
                c.getSaldo(),
                c.getTasaInteres(),
                c.getPlazoMeses(),
                c.getEstado().name(),
                c.getFechaInicio() != null ? c.getFechaInicio().toString() : null,
                c.getFechaVencimiento() != null ? c.getFechaVencimiento().toString() : null
        );
    }

    public CreditoDetailResponse mapToDetail(Credito c) {
        ClienteBasicResponse clienteBasic = clienteMapper.mapToBasic(c.getCliente());
        VentaBasicResponse ventaBasic = c.getVenta() != null ? 
            ventaMapper.mapToBasic(c.getVenta()) : null;
            
        return new CreditoDetailResponse(
            c.getIdCredito(),
            clienteBasic,
            ventaBasic,
            c.getMontoInicial(),
            c.getSaldo(),
            c.getTasaInteres(),
            c.getPlazoMeses(),
            c.getEstado().name(),
            c.getFechaInicio() != null ? c.getFechaInicio().toString() : null,
            c.getFechaVencimiento() != null ? c.getFechaVencimiento().toString() : null
        );
    }
}
