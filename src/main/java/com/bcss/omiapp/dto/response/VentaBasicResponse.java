package com.bcss.omiapp.dto.response;

public record VentaBasicResponse(
    Integer idVenta,
    String nombreSucursal,
    String nombreCliente,
    String nombreTrabajador,
    String fechaVenta,
    Double totalVenta,
    String estado
) {}