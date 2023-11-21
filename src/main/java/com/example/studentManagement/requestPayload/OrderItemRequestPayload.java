package com.example.studentManagement.requestPayload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequestPayload {
    private Long menuItemId;

    private BigDecimal price;
}
