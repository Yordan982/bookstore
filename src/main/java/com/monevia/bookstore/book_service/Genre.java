package com.monevia.bookstore.book_service;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Genre {
    FANTASY, SCI_FI, BIOGRAPHY, ROMANCE, CHILDREN;

    @JsonCreator
    public static Genre fromString(String value) {
        if (value == null) {
            return null;
        }
        return Genre.valueOf(value.toUpperCase());
    }
}