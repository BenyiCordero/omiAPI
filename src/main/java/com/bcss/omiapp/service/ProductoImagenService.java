package com.bcss.omiapp.service;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductoImagenService {
    String save(Integer id, MultipartFile file) throws IOException;
    UrlResource getImages(String filename);
    String getBaseDir();
    List<UrlResource> getImagesByProducto(Integer id);
}
