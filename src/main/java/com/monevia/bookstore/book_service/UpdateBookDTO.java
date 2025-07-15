package com.monevia.bookstore.book_service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateBookDTO {
    @NotNull(message = BookConstants.PRICE_IS_REQUIRED)
    @PositiveOrZero(message = BookConstants.PRICE_NOT_POSITIVE)
    private Double price;
}