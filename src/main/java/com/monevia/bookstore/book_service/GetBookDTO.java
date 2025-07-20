package com.monevia.bookstore.book_service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBookDTO {
    private String title;
    private String author;
    private Double price;
    private Genre genre;
    private Integer quantity;
}