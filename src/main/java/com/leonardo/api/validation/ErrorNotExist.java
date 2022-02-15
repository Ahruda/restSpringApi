package com.leonardo.api.validation;

public class ErrorNotExist {
    private String message;

    public ErrorNotExist(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
