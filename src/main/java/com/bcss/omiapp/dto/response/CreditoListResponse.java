package com.bcss.omiapp.dto.response;

import java.util.List;

public record CreditoListResponse(
    List<CreditoResponse> creditos
) {}