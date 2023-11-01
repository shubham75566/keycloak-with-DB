package com.example.studentManagement.controller;

import com.example.studentManagement.entity.Order;
import com.example.studentManagement.entity.OrderItem;
import com.example.studentManagement.repository.OrderItemRepository;
import com.example.studentManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@GetMapping
	@RequestMapping("/{restaurantId}/list")
//	@PreAuthorize("hasRole('client_manager')")
	// manager can access (suresh)
	public List<Order> getOrders(@PathVariable Long restaurantId) {
		return orderRepository.findByRestaurantId(restaurantId);
    }
	
	@GetMapping
	@RequestMapping("/{orderId}")
//	@PreAuthorize("hasRole('client_manager')")
	// manager can access (suresh)
	public Order getOrderDetails(@PathVariable Long orderId) {
		Order order = orderRepository.findById(orderId).get();
        order.setOrderItems(orderItemRepository.findByOrderId(order.getId()));
        return order;
    }
	
	@PostMapping
	// authenticated users can access
	public Order createOrder(@RequestBody Order order) {
		orderRepository.save(order);
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.id);
            orderItemRepository.save(orderItem);
        });
        return order;
    }
}