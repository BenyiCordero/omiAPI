package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Credito;
import com.bcss.omiapp.dto.response.CreditoResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper {

    public CreditoResponse mapToResponse(Credito c) {
        return new CreditoResponse(
                c.getIdCredito(),
                c.getCliente().getIdCliente(),
                c.getVenta() != null ? c.getVenta().getIdVenta() : null,
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
