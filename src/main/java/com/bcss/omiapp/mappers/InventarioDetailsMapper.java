package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.InventarioDetails;
import com.bcss.omiapp.dto.response.InventarioDetailsBasicResponse;
import com.bcss.omiapp.dto.response.InventarioDetailsDetailResponse;
import com.bcss.omiapp.dto.response.ProductoBasicResponse;
import com.bcss.omiapp.dto.response.InventarioBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class InventarioDetailsMapper {

    private final ProductoMapper productoMapper;

    public InventarioDetailsMapper(ProductoMapper productoMapper) {
        this.productoMapper = productoMapper;
    }

    public InventarioDetailsBasicResponse mapToBasic(InventarioDetails detalle) {
        return new InventarioDetailsBasicResponse(
                detalle.getIdDetalle(),
                detalle.getCantidad(),
                detalle.getEstado(),
                detalle.isDisponible(),
                detalle.getFechaAgregado().toString(),
                detalle.getProducto() != null ? detalle.getProducto().getNombre() : "Sin producto",
                detalle.getInventario() != null ? detalle.getInventario().getTitulo() : "Sin inventario"
        );
    }

    public InventarioDetailsDetailResponse mapToDetail(InventarioDetails detalle) {
        ProductoBasicResponse productoBasic = detalle.getProducto() != null ?
                productoMapper.mapToBasic(detalle.getProducto()) : null;

        InventarioBasicResponse inventarioBasic = detalle.getInventario() != null ?
                new InventarioBasicResponse(
                        detalle.getInventario().getIdInventario(),
                        detalle.getInventario().getTitulo(),
                        detalle.getInventario().getDescripcion(),
                        detalle.getInventario().getFechaCreacion().toString(),
                        detalle.getInventario().getSucursal() != null ?
                                detalle.getInventario().getSucursal().getNombre() : "Sin sucursal"
                ) : null;

        return new InventarioDetailsDetailResponse(
                detalle.getIdDetalle(),
                detalle.getCantidad(),
                detalle.getEstado(),
                detalle.isDisponible(),
                detalle.getFechaAgregado().toString(),
                productoBasic,
                inventarioBasic
        );
    }
}
