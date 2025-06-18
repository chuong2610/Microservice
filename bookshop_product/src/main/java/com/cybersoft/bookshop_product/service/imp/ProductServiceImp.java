package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;
import com.cybersoft.bookshop_product.mapper.ProductMapper;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;
import com.cybersoft.bookshop_product.payload.request.SeachProductRequest;
import com.cybersoft.bookshop_product.repository.ProductRepository;
import com.cybersoft.bookshop_product.service.FileStorageServices;
import com.cybersoft.bookshop_product.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileStorageServices fileStorageServices;

    @Override
    public ProductDTO createProduct(CreateProductRequest request) {
        StringBuilder images = new StringBuilder();
        if(request.getFiles() != null && request.getFiles().length > 0) {
            for (var file : request.getFiles()) {
                fileStorageServices.save(file);
                images.append(file.getOriginalFilename()).append(",");
            }
            images.deleteCharAt(images.length() - 1);
        }
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setAuthor(request.getAuthor());
        product.setReviews(request.getReviews());
        product.setPrice(request.getPrice());
        product.setImages(images.toString());
        return ProductMapper.mapToDTO(productRepository.save(product));


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
        return productRepository.findAll().stream()
                .map( ProductMapper::mapToDTO).toList();
    }

    @Override
    public Page<ProductDTO> searchProduct(SeachProductRequest request) {
        Specification<Product> specification = filter(request.getTitle(), request.getAuthor());
        return productRepository.findAll(specification, PageRequest.of(request.getNumPage(), request.getPageSize()))
                .map(ProductMapper::mapToDTO);


    }
    public Specification<Product> filter(String title, String author) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
