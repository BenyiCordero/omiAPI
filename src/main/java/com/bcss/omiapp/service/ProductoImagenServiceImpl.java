package com.bcss.omiapp.service;

import com.bcss.omiapp.controller.ProductoImagenController;
import com.bcss.omiapp.domain.Producto;
import com.bcss.omiapp.domain.ProductoImagen;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.repository.ProductoImagenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductoImagenServiceImpl implements ProductoImagenService {

    @Value("${product.images.dir}")
    private String dir;
    private final ProductoImagenRepository productoImagenRepository;
    private final ProductoService productoService;

    public ProductoImagenServiceImpl(ProductoImagenRepository productoImagenRepository, ProductoService productoService) {
        this.productoImagenRepository = productoImagenRepository;
        this.productoService = productoService;
    }

    @Override
    public String save(Integer id, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Archivo vacío");
        }

        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Solo se permiten imágenes");
        }

        Path baseDir = Paths.get(dir).toAbsolutePath().normalize();
        Files.createDirectories(baseDir);

        String original = StringUtils.cleanPath(file.getOriginalFilename());
        if (original.contains("..")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nombre de archivo inválido");
        }

        String extension = "";
        int i = original.lastIndexOf('.');
        if (i >= 0) extension = original.substring(i);

        String fileName = UUID.randomUUID().toString() + extension;
        Path target = baseDir.resolve(fileName).normalize().toAbsolutePath();

        if (!target.startsWith(baseDir)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ruta de archivo inválida");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar archivo");
        }

        ProductoImagen pi = ProductoImagen.builder()
                .nombreArchivo(fileName)
                .producto(producto)
                .build();

        productoImagenRepository.save(pi);
        return fileName;
    }

    @Override
    public UrlResource getImages(String filename) {
        try {
            Path baseDir = Paths.get(dir).toAbsolutePath().normalize();
            Path file = baseDir.resolve(filename).normalize().toAbsolutePath();

            if (!file.startsWith(baseDir)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filename inválido");
            }

            UrlResource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagen no encontrada");
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer la imagen");
        }
    }

    @Override
    public List<UrlResource> getImagesByProducto(Integer id) {
        Optional<Producto> producto = productoService.findById(id);
        if(!producto.isPresent()) throw new NotFoundException("Producto no encontrado");

        List<ProductoImagen> imagenes = productoImagenRepository.findByProducto(producto.get());

        try {
            return imagenes.stream()
                    .map(pi -> {
                        try {
                            return getImages(pi.getNombreArchivo());
                        } catch (ResponseStatusException e) {
                            throw e;
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .toList();
        } catch (ResponseStatusException re) {
            throw re;
        } catch (RuntimeException r) {
            if (r.getCause() instanceof ResponseStatusException) {
                throw (ResponseStatusException) r.getCause();
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al cargar imágenes", r);
        }
    }

    @Override
    public String getBaseDir() {
        return dir;
    }

}
