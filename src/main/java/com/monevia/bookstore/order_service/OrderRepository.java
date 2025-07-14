package com.monevia.bookstore.order_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT book_id FROM order_books WHERE order_id = :orderId", nativeQuery = true)
    List<String> findBookIdsByOrderId(@Param("orderId") String orderId);
}