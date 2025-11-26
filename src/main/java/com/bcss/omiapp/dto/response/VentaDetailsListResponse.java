package com.bcss.omiapp.dto.response;

import java.util.List;

public record VentaDetailsListResponse(
    List<VentaDetailsBasicResponse> detalles
) {}