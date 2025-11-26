package com.bcss.omiapp.dto.response;

public record CreditoDetailResponse(
    Integer idCredito,
    ClienteBasicResponse cliente,
    VentaBasicResponse venta,
    Double montoInicial,
    Double saldo,
    Double tasaInteres,
    Integer plazoMeses,
    String estado,
    String fechaInicio,
    String fechaVencimiento
) {}