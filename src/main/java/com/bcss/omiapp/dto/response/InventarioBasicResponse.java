package com.bcss.omiapp.dto.response;

public record InventarioBasicResponse(
    Integer idInventario,
    String titulo,
    String descripcion,
    String fechaCreacion,
    String nombreSucursal
) {}