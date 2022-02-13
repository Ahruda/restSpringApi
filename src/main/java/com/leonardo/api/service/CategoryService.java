package com.leonardo.api.service;

import com.leonardo.api.controller.form.CategoryForm;
import com.leonardo.api.model.Category;
import com.leonardo.api.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    private Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
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
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
