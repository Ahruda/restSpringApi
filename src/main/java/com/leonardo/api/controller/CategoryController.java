package com.leonardo.api.controller;

import com.leonardo.api.controller.form.CategoryForm;
import com.leonardo.api.model.Category;
import com.leonardo.api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryForm categoryForm, UriComponentsBuilder uriBuilder) {
        Category category = categoryService.createCategory(categoryForm);
        URI uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> changeCategory(@PathVariable Long id, @RequestBody @Valid CategoryForm categoryForm) {
        Category category = categoryService.changeCategory(id, categoryForm);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category of id " + id + " deleted with successfully");
    }
}
