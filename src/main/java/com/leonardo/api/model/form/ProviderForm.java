package com.leonardo.api.model.form;

import com.leonardo.api.model.Provider;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProviderForm {

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Number cannot be null")
    @Min(value = 0, message = "Number cannot be 0")
    private int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Provider toProvider() {
        return new Provider(name, number);
    }
}
