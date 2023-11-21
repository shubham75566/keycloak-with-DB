package com.example.studentManagement.responsePayloads;

import com.example.studentManagement.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseItem {
    public Long id;

    private Long orderId;

    private Long menuItemId;

    private BigDecimal price;

    public OrderItemResponseItem(OrderItem item) {

        this.id=item.getOrderId();
        this.menuItemId=item.getMenuItemId();
        this.orderId=item.getOrderId();
        this.price=item.getPrice();

    }
}
