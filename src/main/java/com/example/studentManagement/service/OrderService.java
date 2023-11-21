package com.example.studentManagement.service;

import com.example.studentManagement.requestPayload.OrderRequestPayload;
import com.example.studentManagement.responsePayloads.OrderResponsePayload;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService{
    public List<OrderResponsePayload> findOrdersByRestaurantId(Long restaurantId);

    public OrderResponsePayload getOrderDetails(Long orderId);

    public OrderResponsePayload createOrder(OrderRequestPayload orderRequestPayload);

}
