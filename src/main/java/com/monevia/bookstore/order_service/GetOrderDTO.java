package com.monevia.bookstore.order_service;

import java.math.BigDecimal;
import java.util.List;

public class GetOrderDTO {
    private String customerId;
    private List<String> bookIds;
    private BigDecimal totalAmount;
    private OrderStatus status;

    public GetOrderDTO(String customerId, List<String> bookIds, BigDecimal totalAmount, OrderStatus status) {
        this.customerId = customerId;
        this.bookIds = bookIds;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }
}