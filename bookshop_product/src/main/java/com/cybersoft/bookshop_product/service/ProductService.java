package com.cybersoft.bookshop_product.service;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;

import java.util.List;


public interface ProductService {
    ProductDTO createProduct(CreateProductRequest request);
    ProductDTO updateProduct(String id, ProductDTO productDTO);
    void deleteProduct(String id);
    ProductDTO getProductById(String id);
    List<ProductDTO> getAllProducts();
}
