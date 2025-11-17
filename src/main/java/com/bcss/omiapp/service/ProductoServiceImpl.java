package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Persona;
import com.bcss.omiapp.domain.Producto;
import com.bcss.omiapp.dto.request.ProductoUpdateRequest;
import com.bcss.omiapp.exception.EmptyObject;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NotFoundException("Nombre proporcionado inválido");
        }

        String pref = nombre.trim();
        List<Producto> coincidencias = productoRepository.findByNombreStartingWithIgnoreCase(pref);

        if (coincidencias == null || coincidencias.isEmpty()) {
            throw new NotFoundException("No se encontraron clientes con el prefijo: " + pref);
        }
        return coincidencias;
    }

    @Override
    public Optional<Producto> findById(Integer idProducto) {
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(!producto.isEmpty()) return producto;
        else throw new NotFoundException("Producto no encontrado");
    }

    @Override
    public Producto update(Integer id, ProductoUpdateRequest productoUpdateRequest) {
        if (id == null) {
            throw new NotFoundException("Id del producto inválido");
        }

        Optional<Producto> optExisting = productoRepository.findById(id);
        if (optExisting.isEmpty()) {
            throw new NotFoundException("Producto no encontrado con id: " + id);
        }

        Producto existing = optExisting.get();

        if (productoUpdateRequest.activo()) existing.setActivo(productoUpdateRequest.activo());

        if (productoUpdateRequest.nombre() != null && !productoUpdateRequest.nombre().trim().isEmpty()) {
            existing.setNombre(productoUpdateRequest.nombre().trim());
        }
        if (productoUpdateRequest.marca() != null && !productoUpdateRequest.marca().trim().isEmpty()) {
            existing.setMarca(productoUpdateRequest.marca().trim());
        }
        if (productoUpdateRequest.modelo() != null && !productoUpdateRequest.modelo().trim().isEmpty()) {
            existing.setModelo(productoUpdateRequest.modelo().trim());
        }
        if (productoUpdateRequest.descripcion() != null && !productoUpdateRequest.descripcion().trim().isEmpty()) {
            existing.setDescripcion(productoUpdateRequest.descripcion().trim());
        }
        if (productoUpdateRequest.imei() != null && !productoUpdateRequest.imei().trim().isEmpty()) {
            existing.setImei(productoUpdateRequest.imei().trim());
        }
        if (productoUpdateRequest.precio() != null) {
            existing.setPrecio(productoUpdateRequest.precio());
        }
        if (productoUpdateRequest.costo() != null) {
            existing.setCosto(productoUpdateRequest.costo());
        }

        return productoRepository.save(existing);
    }

    @Override
    public Boolean deleteById(Integer idProducto) {
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()) {
            producto.get().setActivo(false);
            productoRepository.save(producto.get());
            return true;
        }
        else {
            throw new NotFoundException("Producto no encontrado");
        }
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        if(productos.isEmpty()) {
            throw new EmptyObject("No existen productos para mostrar");
        }
        else return productos;
    }
}
