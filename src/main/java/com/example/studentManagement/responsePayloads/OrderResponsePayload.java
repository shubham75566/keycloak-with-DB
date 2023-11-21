package com.example.studentManagement.responsePayloads;

import com.example.studentManagement.entity.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponsePayload {
    public Long id;

    private Long restaurantId;

    private BigDecimal total;

    private List<OrderItemResponseItem> orderItems;

    public OrderResponsePayload(Order order) {

        this.id = order.getId();
        this.total = order.getTotal();
        this.restaurantId = order.getRestaurantId();
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()){
            this.orderItems = order.getOrderItems().stream().map(OrderItemResponseItem::new).toList();
        }
    }
}
