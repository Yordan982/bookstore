package com.monevia.bookstore.book_service;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateBookDTO {
    @Size(max = 50, message = BookConstants.TITLE_MAX_LENGTH)
    private String title;

    @Size(max = 50, message = BookConstants.AUTHOR_MAX_LENGTH)
    private String author;

    @PositiveOrZero(message = BookConstants.PRICE_NOT_POSITIVE)
    private Double price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @PositiveOrZero(message = BookConstants.QUANTITY_NOT_POSITIVE)
    private Integer quantity;
}