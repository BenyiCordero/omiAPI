package com.bcss.omiapp.dto.response;

import java.util.List;

public record InventarioListResponse(
    List<InventarioBasicResponse> inventarios
) {}