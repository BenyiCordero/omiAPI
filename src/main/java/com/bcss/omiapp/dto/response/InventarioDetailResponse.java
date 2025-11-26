package com.bcss.omiapp.dto.response;

import java.util.List;

public record InventarioDetailResponse(
    Integer idInventario,
    String titulo,
    String descripcion,
    String fechaCreacion,
    SucursalBasicResponse sucursal,
    List<InventarioDetailsBasicResponse> detalles
) {}