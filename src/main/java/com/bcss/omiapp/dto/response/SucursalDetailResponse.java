package com.bcss.omiapp.dto.response;

import java.util.List;

public record SucursalDetailResponse(
    Integer idSucursal,
    String nombre,
    String sucursalKey,
    Boolean activo,
    List<TrabajadorBasicResponse> trabajadores
) {}