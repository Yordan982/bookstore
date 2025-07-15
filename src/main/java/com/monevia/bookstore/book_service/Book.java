package com.monevia.bookstore.book_service;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, nullable = false, unique = true)
    private String id;
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
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Book(String title, String author, Double price, Genre genre) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }
}