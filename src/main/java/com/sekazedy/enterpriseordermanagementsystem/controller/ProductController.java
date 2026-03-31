package com.sekazedy.enterpriseordermanagementsystem.controller;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateProductRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.ProductResponse;
import com.sekazedy.enterpriseordermanagementsystem.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return productService.getById(id);
    }
}
