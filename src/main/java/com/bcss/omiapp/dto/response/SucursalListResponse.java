package com.bcss.omiapp.dto.response;

import java.util.List;

public record SucursalListResponse(
    List<SucursalBasicResponse> sucursales
) {}