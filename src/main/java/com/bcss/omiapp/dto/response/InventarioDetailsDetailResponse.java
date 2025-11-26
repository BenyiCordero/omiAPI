package com.bcss.omiapp.dto.response;

public record InventarioDetailsDetailResponse(
    Integer idDetalle,
    Integer cantidad,
    String estado,
    Boolean disponible,
    String fechaAgregado,
    ProductoBasicResponse producto,
    InventarioBasicResponse inventario
) {}