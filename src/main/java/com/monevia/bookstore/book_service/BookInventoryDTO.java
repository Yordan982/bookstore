package com.monevia.bookstore.book_service;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInventoryDTO {
    @PositiveOrZero(message = BookConstants.QUANTITY_NOT_POSITIVE)
    private Integer quantity;
}