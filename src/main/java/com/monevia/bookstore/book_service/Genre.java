package com.monevia.bookstore.book_service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.monevia.bookstore.deserializers.GenreDeserializer;

@JsonDeserialize(using = GenreDeserializer.class)
public enum Genre {
    FANTASY, SCI_FI, BIOGRAPHY, ROMANCE, CHILDREN
}