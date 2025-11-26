package com.bcss.omiapp.dto.request;

import com.bcss.omiapp.domain.EnumEstadoCredito;

public record CreditoRequest(
        Integer idCliente,
        Integer idVenta,
        Double montoInicial,
        Double saldo,
        Double tasaInteres,
        Integer plazoMeses,
        EnumEstadoCredito estado,
        String fechaInicio,
        String fechaVencimiento
) {
}
