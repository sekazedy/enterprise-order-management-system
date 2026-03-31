package com.sekazedy.enterpriseordermanagementsystem.service;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateProductRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.ProductResponse;
import com.sekazedy.enterpriseordermanagementsystem.model.Product;
import com.sekazedy.enterpriseordermanagementsystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(CreateProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice());
        productRepository.save(product);

        return toResponse(product);
    }

    public ProductResponse getById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return toResponse(product);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
