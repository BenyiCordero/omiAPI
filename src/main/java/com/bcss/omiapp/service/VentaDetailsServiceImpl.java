package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Producto;
import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.domain.VentaDetails;
import com.bcss.omiapp.dto.request.VentaDetailsRequest;
import com.bcss.omiapp.dto.response.VentaDetailsListResponse;
import com.bcss.omiapp.dto.response.VentaDetailsDetailResponse;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.repository.ProductoRepository;
import com.bcss.omiapp.repository.VentaDetailsRepository;
import com.bcss.omiapp.repository.VentaRepository;
import com.bcss.omiapp.mappers.VentaDetailsMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class VentaDetailsServiceImpl implements VentaDetailsService {
    private final VentaDetailsRepository repository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final VentaDetailsMapper ventaDetailsMapper;

    public VentaDetailsServiceImpl(VentaDetailsRepository repository, 
                                  VentaRepository ventaRepository, 
                                  ProductoRepository productoRepository,
                                  VentaDetailsMapper ventaDetailsMapper) {
        this.repository = repository;
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.ventaDetailsMapper = ventaDetailsMapper;
    }

    @Override
    public List<VentaDetails> getAll() {
        List<VentaDetails> ventaDetails = repository.findAll();
        if (ventaDetails.isEmpty()) {
            throw new NotFoundException("No hay registros de ventaDetails");
        }
        return ventaDetails;
    }

    @Override
    public VentaDetails getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("VentaDetails no encontrada con id: " + id));
    }

    @Override
    public List<VentaDetails> getByVenta(Integer idVenta) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con id: " + idVenta));

        List<VentaDetails> ventaDetails = repository.findByVenta(venta);
        if (ventaDetails.isEmpty()) throw new NotFoundException("No hay registros de VentaDetails para la venta " + idVenta);
        else return ventaDetails;
    }

    @Override
    public VentaDetails save(VentaDetailsRequest ventaDetailsRequest) {
        Producto producto = productoRepository.findById(ventaDetailsRequest.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + ventaDetailsRequest.idProducto()));

        Venta venta = ventaRepository.findById(ventaDetailsRequest.idVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + ventaDetailsRequest.idVenta()));

        VentaDetails ventaDetails = VentaDetails.builder()
                .producto(producto)
                .venta(venta)
                .cantidad(ventaDetailsRequest.cantidad())
                .precio(ventaDetailsRequest.precio())
                .subtotal(ventaDetailsRequest.subtotal())
                .build();

        return repository.save(ventaDetails);
    }

    @Override
    public VentaDetails update(Integer id, VentaDetailsRequest ventaDetailsRequest) {
        Optional<VentaDetails> optionalExisting = repository.findById(id);
        VentaDetails existing = optionalExisting.get();

        Producto producto = productoRepository.findById(ventaDetailsRequest.idProducto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + ventaDetailsRequest.idProducto()));

        Venta venta = ventaRepository.findById(ventaDetailsRequest.idVenta())
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con id: " + ventaDetailsRequest.idVenta()));

        existing = VentaDetails.builder()
                .idVentaDetails(id)
                .producto(producto)
                .venta(venta)
                .cantidad(ventaDetailsRequest.cantidad())
                .precio(ventaDetailsRequest.precio())
                .subtotal(ventaDetailsRequest.subtotal())
                .build();

        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        VentaDetails existing = getById(id);
        repository.delete(existing);
    }

    @Override
    public VentaDetailsListResponse getAllList() {
        List<VentaDetails> detalles = repository.findAll();
        if (detalles.isEmpty()) {
            throw new NotFoundException("No hay detalles de venta para mostrar");
        }
        return new VentaDetailsListResponse(detalles.stream()
            .map(ventaDetailsMapper::mapToBasic)
            .toList());
    }

    @Override
    public VentaDetailsDetailResponse getByIdDetail(Integer id) {
        VentaDetails detalle = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Detalle de venta no encontrado"));
        return ventaDetailsMapper.mapToDetail(detalle);
    }
}
