package com.monevia.bookstore.user_service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.monevia.bookstore.deserializers.RoleDeserializer;

@JsonDeserialize(using = RoleDeserializer.class)
public enum Role {
    STANDARD, ADMIN
}