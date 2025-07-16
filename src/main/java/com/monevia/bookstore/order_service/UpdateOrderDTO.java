package com.monevia.bookstore.order_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class UpdateOrderDTO {
    @JsonProperty("book_ids")
    private List<String> bookIds;
    private BigDecimal totalAmount;
    private OrderStatus status;
}