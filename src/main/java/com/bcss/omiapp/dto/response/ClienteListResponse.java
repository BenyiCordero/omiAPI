package com.bcss.omiapp.dto.response;

import java.util.List;

public record ClienteListResponse(
    List<ClienteBasicResponse> clientes
) {}