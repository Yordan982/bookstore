package com.monevia.bookstore.book_service;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookDTO {
    @NotBlank(message = BookConstants.TITLE_IS_REQUIRED)
    @Size(max = 50, message = BookConstants.TITLE_MAX_LENGTH)
    private String title;

    @NotBlank(message = BookConstants.AUTHOR_IS_REQUIRED)
    @Size(max = 50, message = BookConstants.AUTHOR_MAX_LENGTH)
    private String author;

    @NotNull(message = BookConstants.PRICE_IS_REQUIRED)
    @PositiveOrZero(message = BookConstants.PRICE_NOT_POSITIVE)
    private Double price;

    @NotNull(message = BookConstants.GENRE_IS_REQUIRED)
    private Genre genre;

    @NotNull(message = BookConstants.QUANTITY_IS_REQUIRED)
    @PositiveOrZero(message = BookConstants.QUANTITY_NOT_POSITIVE)
    private Integer quantity;
}