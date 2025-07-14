package com.monevia.bookstore.order_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderDTO {
    @Column(name = "customer_id")
    @NotBlank(message = OrderConstants.CUSTOMER_ID_IS_REQUIRED)
    private String customerId;
    @JsonProperty("book_ids")
    private List<String> bookIds;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    private OrderStatus status;

    public CreateOrderDTO(List<String> bookIds) {
        this.bookIds = bookIds;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CreateOrderDTO setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public CreateOrderDTO setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public CreateOrderDTO setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public CreateOrderDTO setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}