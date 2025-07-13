package com.monevia.bookstore.book_service;

public class GetBookDTO {
    private String title;
    private Double price;

    public GetBookDTO(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }
}