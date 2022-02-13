package com.leonardo.api.controller.form;

import com.leonardo.api.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryForm {

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 20, message = "About Me must be between 10 and 200 characters")
    private String name;

    public CategoryForm() {
    }

    public CategoryForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toCategory() {
        return new Category(name);
    }
}
