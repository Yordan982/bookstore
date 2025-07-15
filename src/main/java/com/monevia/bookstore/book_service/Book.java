package com.monevia.bookstore.book_service;

import jakarta.persistence.*;
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
    private String title;
    private String author;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Book(String title, String author, Double price, Genre genre) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }
}