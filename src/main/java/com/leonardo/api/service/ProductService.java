package com.leonardo.api.service;

import com.leonardo.api.model.Product;
import com.leonardo.api.model.form.ProductForm;
import com.leonardo.api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderService providerService;

    @Autowired
    CategoryService categoryService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NoSuchElementException("Element of id " + id + " does not exist");
    }

    @Transactional
    public Product createProduct(ProductForm productForm) {
        Product product = productForm.toProduct(providerService, categoryService);
        productRepository.save(product);
        return product;
    }

    @Transactional
    public Product changeProduct(ProductForm productForm, Long id) {
        Product product = getProductById(id);
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setCategory(categoryService.getCategoryById(productForm.getCategoryId()));
        product.setProvider(providerService.getProviderById(productForm.getProviderId()));
        return product;
    }

    @Transactional
    public ResponseEntity<String> deleteProduct(Long id) {
        getProductById(id);
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product of id " + id + " deleted with successfully");
    }

    public List<Product> getProductByCategory(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public List<Product> getProductsByProvider(Long id) {
        return productRepository.findByProviderId(id);
    }
}
