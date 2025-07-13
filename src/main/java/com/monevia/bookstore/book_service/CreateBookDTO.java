package com.monevia.bookstore.book_service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CreateBookDTO {
    @NotNull(message = BookConstants.TITLE_IS_REQUIRED)
    @Size(max = 50, message = BookConstants.TITLE_MAX_LENGTH)
    private String title;

    @NotNull(message = BookConstants.AUTHOR_IS_REQUIRED)
    @Size(max = 50, message = BookConstants.AUTHOR_MAX_LENGTH)
    private String author;

    @NotNull(message = BookConstants.PRICE_IS_REQUIRED)
    @PositiveOrZero(message = BookConstants.PRICE_NOT_POSITIVE)
    private Double price;

    @NotNull(message = BookConstants.GENRE_IS_REQUIRED)
    private Genre genre;

    public CreateBookDTO(String title, String author, Double price, Genre genre) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public CreateBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CreateBookDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public CreateBookDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public CreateBookDTO setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }
}