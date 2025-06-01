package com.cybersoft.bookshop_product.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private String id;
    private String title;
    private String author;
    private int review;
    private double price;
    private String images;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Getters and Setters
}
