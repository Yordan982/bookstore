package com.monevia.bookstore.order_service;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;

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

    public Order() {
    }

    public Order(String customerId, List<String> bookIds, BigDecimal totalAmount, OrderStatus status) {
        this.customerId = customerId;
        this.bookIds = bookIds;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Order setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public Order setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Order setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}