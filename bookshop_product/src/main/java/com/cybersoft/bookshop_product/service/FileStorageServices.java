package com.cybersoft.bookshop_product.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServices {
    void save(MultipartFile file);
    Resource load(String filename);
}
