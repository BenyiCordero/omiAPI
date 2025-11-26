package com.bcss.omiapp.dto.response;

import java.util.List;

public record InventarioDetailsListResponse(
    List<InventarioDetailsBasicResponse> detalles
) {}