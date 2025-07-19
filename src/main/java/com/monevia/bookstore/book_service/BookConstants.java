package com.monevia.bookstore.book_service;

public class BookConstants {
    public static final String TITLE_IS_REQUIRED = "Title is required";
    public static final String TITLE_MAX_LENGTH = "Title must be {max} characters or fewer";
    public static final String AUTHOR_IS_REQUIRED = "Author is required";
    public static final String AUTHOR_MAX_LENGTH = "Author must be {max} characters or fewer";
    public static final String PRICE_IS_REQUIRED = "Price is required";
    public static final String PRICE_NOT_POSITIVE = "Price must be zero or positive";
    public static final String GENRE_IS_REQUIRED = "Genre is required";
    public static final String QUANTITY_NOT_POSITIVE = "Quantity must be greater than or equal to zero";
    public static final String QUANTITY_UPDATED = "Quantity updated successfully to %d";
    public static final String QUANTITY_ADDED = "Quantity has been increased by %d";
    public static final String QUANTITY_REMOVED = "Quantity has been decreased by %d";
    public static final String QUANTITY_INSUFFICIENT = "Insufficient quantity: only %s available";
    public static final String BOOK_CREATED = "Book created successfully";
    public static final String BOOK_UPDATED = "Book updated successfully";
    public static final String BOOK_DELETED = "Book deleted successfully";
    public static final String BOOK_NOT_FOUND = "Book not found";
    public static final String BOOK_INVENTORY_NOT_FOUND = "Book inventory not found";
}