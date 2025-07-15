package com.monevia.bookstore.book_service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class UpdateBookDTO {
    @NotNull(message = BookConstants.PRICE_IS_REQUIRED)
    @PositiveOrZero(message = BookConstants.PRICE_NOT_POSITIVE)
    private Double price;

    public UpdateBookDTO(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public UpdateBookDTO setPrice(Double price) {
        this.price = price;
        return this;
    }
}