package com.bcss.omiapp.dto.response;

public record InventarioDetailsBasicResponse(
    Integer idDetalle,
    Integer cantidad,
    String estado,
    Boolean disponible,
    String fechaAgregado,
    String nombreProducto,
    String nombreInventario
) {}