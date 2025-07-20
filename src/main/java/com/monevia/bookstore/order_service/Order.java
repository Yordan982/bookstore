package com.monevia.bookstore.order_service;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String id;

    @NotBlank(message = OrderConstants.CUSTOMER_ID_IS_REQUIRED)
    @Column(name = "customer_id")
    private String customerId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "book_id")
    private List<String> bookIds;

    @NotNull(message = OrderConstants.TOTAL_AMOUNT_IS_REQUIRED)
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(String customerId, List<String> bookIds, BigDecimal totalAmount, OrderStatus status) {
        this.customerId = customerId;
        this.bookIds = bookIds;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}