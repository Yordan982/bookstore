package com.monevia.bookstore.order_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
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
}