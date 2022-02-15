package com.leonardo.api.model.form;

import com.leonardo.api.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryForm {

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    public String getName() {
        return name;
    }

    public Category toCategory() {
        return new Category(name);
    }
}
