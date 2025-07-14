package com.monevia.bookstore.order_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestBody @Validated CreateOrderDTO createOrderDTO) {
        Order order = orderService.createOrder(createOrderDTO);
        Map<String, Object> responseBody = Map.of(
                "message", OrderConstants.ORDER_CREATED,
                "order_id", order.getId(),
                "total_amount", order.getTotalAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}