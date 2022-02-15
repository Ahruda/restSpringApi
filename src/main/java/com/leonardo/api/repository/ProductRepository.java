package com.leonardo.api.repository;

import com.leonardo.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long id);
    List<Product> findByProviderId(Long id);

}
