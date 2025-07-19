package com.monevia.bookstore.book_service;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
public class BookInventory {
    @Id
    @Column(length = 36, nullable = false, unique = true)
    private String bookId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "book_id")
    private Book book;

    @PositiveOrZero(message = BookConstants.QUANTITY_NOT_POSITIVE)
    private Integer quantity;

    public BookInventory(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }
}