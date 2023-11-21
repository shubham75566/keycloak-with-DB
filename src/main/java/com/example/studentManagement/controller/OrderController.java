package com.example.studentManagement.controller;

import com.example.studentManagement.repository.OrderItemRepository;
import com.example.studentManagement.repository.OrderRepository;
import com.example.studentManagement.requestPayload.OrderRequestPayload;
import com.example.studentManagement.responsePayloads.OrderResponsePayload;
import com.example.studentManagement.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = "keycloak")
public class OrderController {

    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderService orderService;

    @GetMapping
    @RequestMapping("/{restaurantId}/list")
    // manager can access (suresh)
    public ResponseEntity<List<OrderResponsePayload>> getOrders(@PathVariable Long restaurantId) {

        try {
            log.debug("get orders request with restaurant id : {}",restaurantId);
            return new ResponseEntity<>(orderService.findOrdersByRestaurantId(restaurantId), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurant by id where restaurantId : {}", restaurantId, e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("/{orderId}")
    // manager can access (suresh)
    public ResponseEntity<OrderResponsePayload> getOrderDetails(@PathVariable Long orderId) {

        try {
            log.debug("get order details request with order id : {}",orderId);
            return new ResponseEntity<>(orderService.getOrderDetails(orderId), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurant by id where restaurantId : {}", orderId, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    // authenticated users can access
    public ResponseEntity<OrderResponsePayload> createOrder(@RequestBody OrderRequestPayload order) {

        try {
            log.debug("create order request with payload : {}",order);
            return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while creating order with payload : {} ", order, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}