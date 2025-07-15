package com.monevia.bookstore.order_service;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, nullable = false, unique = true)
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "book_id")
    private List<String> bookIds;

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