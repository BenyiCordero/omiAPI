package com.bcss.omiapp.dto.response;

public record CreditoResponse(
        Integer idCredito,
        Integer idCliente,
        Integer idVenta,
        Double montoInicial,
        Double saldo,
        Double tasaInteres,
        Integer plazoMeses,
        String estado,
        String fechaInicio,
        String fechaVencimiento
) {
}
