package com.monevia.bookstore.errors;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationErrorResponse {
    private Map<String, String> errors;

    public ValidationErrorResponse(Map<String, String> errors) {
        this.errors = errors;
    }
}