package com.leonardo.api.validation;

public class ErrorSql {
    private String message;

    public ErrorSql(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
