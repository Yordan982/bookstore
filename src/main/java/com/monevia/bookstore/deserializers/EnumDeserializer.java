package com.monevia.bookstore.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class EnumDeserializer<T extends Enum<T>> extends JsonDeserializer<T> {
    private final Class<T> enumClass;

    public EnumDeserializer(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String value = jsonParser.getText();
        if (value == null) return null;
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}