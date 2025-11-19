package com.bcss.omiapp.controller;

import com.bcss.omiapp.service.ProductoImagenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productImage")
public class ProductoImagenController {

    private final ProductoImagenService productoImagenService;

    public ProductoImagenController(ProductoImagenService productoImagenService) {
        this.productoImagenService = productoImagenService;
    }

    @PostMapping(path = "/{id}/imagenes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImagen(@PathVariable Integer id,
                                          @RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) throws IOException {
        String filename = productoImagenService.save(id, file);
        String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
        String url = baseUrl + "/images/" + filename;
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("filename", filename, "url", url));
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<?> serveImage(@PathVariable String filename) {
        UrlResource resource = productoImagenService.getImages(filename);
        try {
            Path baseDir = Paths.get(productoImagenService.getBaseDir())
                    .toAbsolutePath()
                    .normalize();

            Path file = baseDir.resolve(filename).normalize();
            String contentType = Files.probeContentType(file);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE,
                            contentType != null ? contentType : MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/imagenes")
    public ResponseEntity<?> getImagenesByProducto(@PathVariable Integer id, HttpServletRequest request) {
        List<UrlResource> resources = productoImagenService.getImagesByProducto(id);

        String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());

        List<Map<String, String>> imgs = resources.stream()
                .map(res -> {
                    String filename = res.getFilename();
                    String url = baseUrl + "/images/" + filename;
                    return Map.of("filename", filename, "url", url);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("productoId", id, "imagenes", imgs));
    }

}
