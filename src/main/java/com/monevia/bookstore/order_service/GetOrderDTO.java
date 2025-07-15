package com.monevia.bookstore.order_service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class GetOrderDTO {
    private String customerId;
    private List<String> bookIds;
    private BigDecimal totalAmount;
    private OrderStatus status;
}