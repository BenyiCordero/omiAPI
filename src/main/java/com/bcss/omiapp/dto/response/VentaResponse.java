package com.bcss.omiapp.dto.response;

import com.bcss.omiapp.domain.Cliente;
import com.bcss.omiapp.domain.EnumEstadoVenta;
import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.dto.response.TrabajadorResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public record VentaResponse(
        Integer idVenta,
        Sucursal sucursal,
        Cliente cliente,
        TrabajadorResponse trabajador,
        LocalDate fechaVenta,
        Double totalVenta,
        Double descuento,
        Double impuesto,
        EnumEstadoVenta estado,
        String notas
) {}
