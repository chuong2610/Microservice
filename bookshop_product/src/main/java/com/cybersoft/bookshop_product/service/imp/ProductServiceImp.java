package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public ProductDTO getProductById(String id) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return List.of();
    }
}
