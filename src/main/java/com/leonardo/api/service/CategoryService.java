package com.leonardo.api.service;

import com.leonardo.api.model.Category;
import com.leonardo.api.model.form.CategoryForm;
import com.leonardo.api.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NoSuchElementException("Element of id " + id + " does not exist");
    }

    @Transactional
    public Category createCategory(CategoryForm categoryForm) {
        Category category = categoryForm.toCategory();
        categoryRepository.save(category);
        return category;
    }

    @Transactional
    public Category changeCategory(Long id, CategoryForm categoryForm) {
        Category category = getCategoryById(id);
        category.setName(categoryForm.getName());
        return category;
    }

    @Transactional
    public ResponseEntity<String> deleteCategory(Long id) {
        getCategoryById(id);
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Category of id " + id + " deleted with successfully");
    }
}
