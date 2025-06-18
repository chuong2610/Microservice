package com.cybersoft.bookshop_product.controller;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;
import com.cybersoft.bookshop_product.payload.request.SeachProductRequest;
import com.cybersoft.bookshop_product.payload.response.BaseRespone;
import com.cybersoft.bookshop_product.service.FileStorageServices;
import com.cybersoft.bookshop_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        BaseRespone respone = new BaseRespone();
        respone.setCode(HttpStatus.OK.value());
        respone.setMessage("success");
        respone.setData(productService.getAllProducts());
        return ResponseEntity.ok(respone);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(CreateProductRequest request) {
        BaseRespone respone = new BaseRespone();
        respone.setCode(HttpStatus.CREATED.value());
        respone.setMessage("Product created successfully");
        respone.setData(productService.createProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(respone);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(SeachProductRequest request) {
        BaseRespone respone = new BaseRespone();
        respone.setCode(HttpStatus.OK.value());
        respone.setMessage("Search results");
        respone.setData(productService.searchProduct(request).getContent());
        return ResponseEntity.ok(respone);
    }
}
