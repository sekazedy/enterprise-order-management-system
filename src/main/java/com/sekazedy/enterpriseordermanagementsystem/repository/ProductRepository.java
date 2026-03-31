package com.sekazedy.enterpriseordermanagementsystem.repository;

import com.sekazedy.enterpriseordermanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
