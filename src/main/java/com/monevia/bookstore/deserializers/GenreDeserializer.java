package com.monevia.bookstore.deserializers;

import com.monevia.bookstore.book_service.Genre;

public class GenreDeserializer extends EnumDeserializer<Genre> {

    public GenreDeserializer() {
        super(Genre.class);
    }
}