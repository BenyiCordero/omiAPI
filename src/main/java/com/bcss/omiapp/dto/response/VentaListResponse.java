package com.bcss.omiapp.dto.response;

import java.util.List;

public record VentaListResponse(
    List<VentaBasicResponse> ventas
) {}