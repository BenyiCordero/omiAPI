package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.VentaDetails;
import com.bcss.omiapp.dto.response.VentaDetailsBasicResponse;
import com.bcss.omiapp.dto.response.VentaDetailsDetailResponse;
import com.bcss.omiapp.dto.response.ProductoBasicResponse;
import com.bcss.omiapp.dto.response.VentaBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class VentaDetailsMapper {
    
    private final ProductoMapper productoMapper;
    private final VentaMapper ventaMapper;

    public VentaDetailsMapper(ProductoMapper productoMapper, VentaMapper ventaMapper) {
        this.productoMapper = productoMapper;
        this.ventaMapper = ventaMapper;
    }

    public VentaDetailsBasicResponse mapToBasic(VentaDetails detalle) {
        return new VentaDetailsBasicResponse(
            detalle.getIdVentaDetails(),
            detalle.getCantidad(),
            detalle.getPrecio(),
            detalle.getSubtotal(),
            detalle.getProducto() != null ? detalle.getProducto().getNombre() : "Sin producto",
            detalle.getVenta() != null ? detalle.getVenta().getIdVenta() : null
        );
    }

    public VentaDetailsDetailResponse mapToDetail(VentaDetails detalle) {
        ProductoBasicResponse productoBasic = detalle.getProducto() != null ? 
            productoMapper.mapToBasic(detalle.getProducto()) : null;
            
        VentaBasicResponse ventaBasic = detalle.getVenta() != null ? 
            ventaMapper.mapToBasic(detalle.getVenta()) : null;
            
        return new VentaDetailsDetailResponse(
            detalle.getIdVentaDetails(),
            detalle.getCantidad(),
            detalle.getPrecio(),
            detalle.getSubtotal(),
            productoBasic,
            ventaBasic
        );
    }
}