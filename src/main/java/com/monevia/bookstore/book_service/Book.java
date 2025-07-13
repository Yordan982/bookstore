package com.monevia.bookstore.book_service;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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

    public Book() {
    }

    public Book(String title, String author, Double price, Genre genre) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public Book setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Book setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public Book setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }
}