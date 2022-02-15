package com.leonardo.api.model.form;

import com.leonardo.api.model.Category;
import com.leonardo.api.model.Product;
import com.leonardo.api.model.Provider;
import com.leonardo.api.service.CategoryService;
import com.leonardo.api.service.ProviderService;
import jakarta.validation.constraints.*;

public class ProductForm {

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMax(value = "9999.00", message = "Price cannot exceed 9999.00")
    @DecimalMin(value= "0.00", message = "Price cannot be less than 0.00")
    private double price;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Quantity cannot be less than 0")
    private int quantity;

    @NotNull(message = "Category id cannot be null")
    @Min(value = 1, message = "Category id cannot be 0")
    private Long categoryId;

    @NotNull(message = "Provider id cannot be null")
    @Min(value = 1, message = "Provider id cannot be 0")
    private Long providerId;

    public ProductForm(int id, String name, double price, int quantity, Long categoryId, Long providerId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public Product toProduct(ProviderService providerService, CategoryService categoryService) {
        Provider provider = providerService.getProviderById(providerId);
        Category category = categoryService.getCategoryById(categoryId);
        return(new Product(name,price,quantity,category,provider));
    }

}
