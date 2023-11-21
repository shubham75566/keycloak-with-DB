package com.example.studentManagement.requestPayload;

import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

@Data
public class MenuRequestPayload {

    Long restaurantId;

    Boolean active;

    @Transient
    private List<MenuItemRequestPayload> menuItems;
}
