package com.bcss.omiapp.dto.response;

public record VentaDetailsDetailResponse(
    Integer idVentaDetails,
    Integer cantidad,
    Double precio,
    Double subtotal,
    ProductoBasicResponse producto,
    VentaBasicResponse venta
) {}