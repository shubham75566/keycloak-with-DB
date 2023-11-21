package com.example.studentManagement.requestPayload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemRequestPayload {

    private Long menuId;

    private String name;

    private String description;

    private String type;

    private String group;

    private BigDecimal price;

}
