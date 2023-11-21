package com.example.studentManagement.service.impl;

import com.example.studentManagement.entity.Order;
import com.example.studentManagement.entity.OrderItem;
import com.example.studentManagement.repository.OrderItemRepository;
import com.example.studentManagement.repository.OrderRepository;
import com.example.studentManagement.requestPayload.OrderItemRequestPayload;
import com.example.studentManagement.requestPayload.OrderRequestPayload;
import com.example.studentManagement.responsePayloads.OrderItemResponseItem;
import com.example.studentManagement.responsePayloads.OrderResponsePayload;
import com.example.studentManagement.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderResponsePayload> findOrdersByRestaurantId(Long restaurantId) {

        try {
            log.debug("{} : get order request with restaurant id : {}",Thread.currentThread().getStackTrace()[0].getMethodName(),restaurantId);
            List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
            orders.forEach(m->m.setOrderItems(orderItemRepository.findByOrderId(m.getId())));
            return orders.stream().map(OrderResponsePayload::new).toList();
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurant by id where restaurantId : {}", restaurantId, e);
            return Collections.emptyList();
        }
    }

    @Override
    public OrderResponsePayload getOrderDetails(Long orderId) {
        log.debug("{} : get orders details request with order id : {}",Thread.currentThread().getStackTrace()[0].getMethodName(),orderId);

        OrderResponsePayload orderResponsePayload = null;
        try {
            Order order = orderRepository.findById(orderId).get();
            order.setOrderItems(orderItemRepository.findByOrderId(order.getId()));
            orderResponsePayload = new OrderResponsePayload(order);
            return orderResponsePayload;
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurant by id where restaurantId : {}", orderId, e);
            return orderResponsePayload;
        }
    }
    @Override
    public OrderResponsePayload createOrder(OrderRequestPayload orderRequestPayload) {

        log.debug("{} : create order request with restaurant payload : {}",Thread.currentThread().getStackTrace()[0].getMethodName(),orderRequestPayload);

        OrderResponsePayload orderResponsePayload = null;
        try {
            Order order = orderRepository.save(new Order(orderRequestPayload));
            List<OrderItemResponseItem> orderItems = new ArrayList<>();
            orderRequestPayload.getOrderItems().forEach(orderItem -> orderItems.add(new OrderItemResponseItem(orderItemRepository.save(new OrderItem(orderItem,order.id)))));
            orderResponsePayload=new OrderResponsePayload(order);
            orderResponsePayload.setOrderItems(orderItems);
            return orderResponsePayload;
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurant by id where restaurantId : {}", orderRequestPayload, e);
            return orderResponsePayload;
        }
    }

}
