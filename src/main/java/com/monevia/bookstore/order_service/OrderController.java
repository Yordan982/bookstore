package com.monevia.bookstore.order_service;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestBody @Valid CreateOrderDTO createOrderDTO) {
        Order order = orderService.createOrder(createOrderDTO);
        Map<String, Object> responseBody = Map.of(
                "message", OrderConstants.ORDER_CREATED,
                "order_id", order.getId(),
                "total_amount", order.getTotalAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(value = "/{order_id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetOrderDTO> getOrder(
            @PathVariable("order_id") String orderId) {
        GetOrderDTO getOrderDTO = orderService.getOrder(orderId);
        return ResponseEntity.ok(getOrderDTO);
    }
}