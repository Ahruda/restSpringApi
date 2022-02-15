package com.leonardo.api.controller;

import com.leonardo.api.model.form.ProductForm;
import com.leonardo.api.model.Product;
import com.leonardo.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{id}")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
        return productService.getProductByCategory(id);
    }

    @GetMapping("/provider/{id}")
    public List<Product> getProductsByProvider(@PathVariable Long id) {
        return productService.getProductsByProvider(id);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriBuilder) {
        Product product = productService.createProduct(productForm);
        URI uri = uriBuilder.path("/category/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> changeProduct(@RequestBody @Valid ProductForm productForm, @PathVariable Long id) {
        Product product = productService.changeProduct(productForm, id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
