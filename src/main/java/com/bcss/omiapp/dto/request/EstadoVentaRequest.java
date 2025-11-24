package com.bcss.omiapp.dto.request;

import com.bcss.omiapp.domain.EnumEstadoVenta;

public record EstadoVentaRequest(
        EnumEstadoVenta estado
) {
}
