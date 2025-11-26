package com.bcss.omiapp.dto.request;

import com.bcss.omiapp.domain.EnumEstadoCredito;

public record EstadoCreditoRequest(
        EnumEstadoCredito estado
) {
}
