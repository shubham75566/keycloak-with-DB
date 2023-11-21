package com.example.studentManagement.requestPayload;

import com.example.studentManagement.entity.OrderItem;
import com.example.studentManagement.responsePayloads.OrderItemResponseItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestPayload {
    private Long restaurantId;

    private BigDecimal total;

    private List<OrderItemRequestPayload> orderItems;

}
