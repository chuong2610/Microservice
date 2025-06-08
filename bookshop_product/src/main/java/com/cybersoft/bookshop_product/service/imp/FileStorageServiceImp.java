package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.exception.FileStorageException;
import com.cybersoft.bookshop_product.service.FileStorageServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImp implements FileStorageServices {
    @Value("${file.path}")
    private String filePath;

    @Override
    public void save(MultipartFile file) {
        try{
            Path rootPath = Path.of(filePath);
            if(!Files.exists(rootPath)) {
                Files.createDirectories(rootPath);
            }
            Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()),
            StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new FileStorageException("Failed to store file " + file.getOriginalFilename()+" - "+ e.getMessage());
        }

    }

    @Override
    public Resource load(String filename) {
        // Implement file loading logic here
        return null; // Placeholder return statement
    }
}
