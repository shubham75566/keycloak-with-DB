package com.example.studentManagement.responsePayloads;

import com.example.studentManagement.entity.Menu;
import com.example.studentManagement.entity.MenuItem;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

@Data
public class MenuResponsePayload {

    public Long id;

    private Long restaurantId;

    private Boolean active;

    private List<MenuItemResponsePayload> menuItems;

    public MenuResponsePayload(Menu menu) {

        this.active=menu.getActive();
        this.restaurantId= menu.getRestaurantId();
        this.id=menu.id;
        if (menu.getMenuItems() != null && !menu.getMenuItems().isEmpty()){
            this.menuItems=menu.getMenuItems().stream().map(MenuItemResponsePayload::new).toList();
        }
    }
}
