package com.cybersoft.bookshop_product.service;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;
import com.cybersoft.bookshop_product.payload.request.SeachProductRequest;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.List;


public interface ProductService {
    ProductDTO createProduct(CreateProductRequest request);
    ProductDTO updateProduct(String id, ProductDTO productDTO);
    void deleteProduct(String id);
    ProductDTO getProductById(String id);
    List<ProductDTO> getAllProducts();
    Page<ProductDTO> searchProduct(SeachProductRequest request);
}
