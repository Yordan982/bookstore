package com.monevia.bookstore.order_service;

import com.monevia.bookstore.book_service.Book;
import com.monevia.bookstore.book_service.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public Order createOrder(CreateOrderDTO createOrderDTO) {
        List<Book> foundBooks = bookRepository.findAllById(createOrderDTO.getBookIds());
        if (foundBooks.isEmpty()) {
            throw new IllegalArgumentException(OrderConstants.BOOK_IDS_IS_REQUIRED);
        }
        List<String> validBookIds = foundBooks.stream()
                .map(Book::getId)
                .toList();
        BigDecimal totalAmount = calculateTotalAmount(foundBooks);
        Order order = createNewOrder(validBookIds, totalAmount);
        return orderRepository.save(order);
    }

    private Order createNewOrder(List<String> bookIds, BigDecimal totalAmount) {
        return new Order(bookIds, totalAmount, OrderStatus.CREATED);
    }

    private BigDecimal calculateTotalAmount(List<Book> books) {
        return books.stream()
                .map(book -> BigDecimal.valueOf(book.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}