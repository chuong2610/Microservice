package com.cybersoft.bookshop_product.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CreateProductRequest {
    private String title;
    private String author;
    private int reviews;
    private double price;
    private MultipartFile[] files;

}
