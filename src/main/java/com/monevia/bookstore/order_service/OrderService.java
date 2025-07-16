package com.monevia.bookstore.order_service;

import com.monevia.bookstore.book_service.Book;
import com.monevia.bookstore.book_service.BookRepository;
import com.monevia.bookstore.user_service.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository, UserRepository userRepository, OrderMapper bookMapper) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.orderMapper = bookMapper;
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
        Order order = new Order(createOrderDTO.getCustomerId(), validBookIds, totalAmount, OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    public GetOrderDTO getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException(OrderConstants.ORDER_NOT_FOUND));
        List<String> booksIds = orderRepository.findBookIdsByOrderId(orderId);
        return new GetOrderDTO(order.getCustomerId(), booksIds, order.getTotalAmount(), order.getStatus());
    }

    public void updateOrder(String orderId, UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        OrderConstants.ORDER_NOT_FOUND));
        if (updateOrderDTO.getBookIds() != null && !updateOrderDTO.getBookIds().isEmpty()) {
            List<Book> foundBooks = bookRepository.findAllById(updateOrderDTO.getBookIds());
            List<String> validBookIds = foundBooks.stream()
                    .map(Book::getId)
                    .toList();
            updateOrderDTO.setBookIds(validBookIds);
            if (updateOrderDTO.getTotalAmount() == null) {
                updateOrderDTO.setTotalAmount(calculateTotalAmount(foundBooks));
            }
            order.getBookIds().clear();
            order.getBookIds().addAll(validBookIds);
        }
        orderMapper.updateOrderFromDTO(updateOrderDTO, order);
        orderRepository.save(order);
    }

    private boolean userExists(String userId) {
        return userRepository.existsById(userId);
    }

    private BigDecimal calculateTotalAmount(List<Book> books) {
        return books.stream()
                .map(book -> BigDecimal.valueOf(book.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}