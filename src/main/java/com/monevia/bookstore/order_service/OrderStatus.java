package com.monevia.bookstore.order_service;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {
    CREATED, PENDING, PROGRESS, SENT, COMPLETED, CANCELLED;

    @JsonCreator
    public static OrderStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        return OrderStatus.valueOf(value.toUpperCase());
    }
}