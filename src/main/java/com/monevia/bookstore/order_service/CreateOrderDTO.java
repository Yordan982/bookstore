package com.monevia.bookstore.order_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    @NotBlank(message = OrderConstants.CUSTOMER_ID_IS_REQUIRED)
    private String customerId;

    @JsonProperty("book_ids")
    private List<String> bookIds;

    public CreateOrderDTO(List<String> bookIds) {
        this.bookIds = bookIds;
    }
}