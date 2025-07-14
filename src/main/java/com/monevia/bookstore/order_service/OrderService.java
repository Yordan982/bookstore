package com.monevia.bookstore.order_service;

import com.monevia.bookstore.book_service.Book;
import com.monevia.bookstore.book_service.BookRepository;
import com.monevia.bookstore.user_service.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(CreateOrderDTO createOrderDTO) {
        List<Book> foundBooks = bookRepository.findAllById(createOrderDTO.getBookIds());
        if (foundBooks.isEmpty()) {
            throw new IllegalArgumentException(OrderConstants.BOOK_IDS_IS_REQUIRED);
        }
        if (!userExists(createOrderDTO.getCustomerId())) {
            throw new IllegalArgumentException(OrderConstants.CUSTOMER_ID_NOT_FOUND);
        }
        List<String> validBookIds = foundBooks.stream()
                .map(Book::getId)
                .toList();
        BigDecimal totalAmount = calculateTotalAmount(foundBooks);
        Order order = createNewOrder(createOrderDTO.getCustomerId(), validBookIds, totalAmount);
        return orderRepository.save(order);
    }

    private Order createNewOrder(String customerId, List<String> bookIds, BigDecimal totalAmount) {
        return new Order(customerId, bookIds, totalAmount, OrderStatus.CREATED);
    }

    private BigDecimal calculateTotalAmount(List<Book> books) {
        return books.stream()
                .map(book -> BigDecimal.valueOf(book.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean userExists(String userId) {
        return userRepository.existsById(userId);
    }

    public GetOrderDTO getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException(OrderConstants.ORDER_NOT_FOUND));
        List<String> booksIds = orderRepository.findBookIdsByOrderId(orderId);
        return new GetOrderDTO(order.getCustomerId(), booksIds, order.getTotalAmount(), order.getStatus());
    }
}