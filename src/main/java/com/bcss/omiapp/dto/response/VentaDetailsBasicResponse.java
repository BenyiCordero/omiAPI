package com.bcss.omiapp.dto.response;

public record VentaDetailsBasicResponse(
    Integer idVentaDetails,
    Integer cantidad,
    Double precio,
    Double subtotal,
    String nombreProducto,
    Integer idVenta
) {}