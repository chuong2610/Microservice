package com.cybersoft.bookshop_product.mapper;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;

public class ProductMapper {
    public static ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setAuthor(product.getAuthor());
        dto.setReview(product.getReviews());
        dto.setPrice(product.getPrice());
        dto.setImages(product.getImages());
        dto.setCreatedDate(product.getCreatedDate());
        dto.setUpdatedDate(product.getUpdatedDate());
        return dto;
    }

}
