package com.bcss.omiapp.dto.response;

import com.bcss.omiapp.domain.EnumEstadoVenta;
import java.time.LocalDate;

public record VentaDetailResponse(
    Integer idVenta,
    SucursalBasicResponse sucursal,
    ClienteBasicResponse cliente,
    TrabajadorBasicResponse trabajador,
    LocalDate fechaVenta,
    Double totalVenta,
    Double descuento,
    Double impuesto,
    EnumEstadoVenta estado,
    String notas
) {}