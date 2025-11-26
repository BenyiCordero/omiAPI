package com.bcss.omiapp.dto.response;

import java.util.List;

public record ProductoListResponse(
    List<ProductoBasicResponse> productos
) {}