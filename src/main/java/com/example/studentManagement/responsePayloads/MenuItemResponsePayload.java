package com.example.studentManagement.responsePayloads;

import com.example.studentManagement.entity.MenuItem;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuItemResponsePayload {

    public Long id;

    private Long menuId;

    private String name;

    private String description;

    @Column(name = "type_name")
    private String type;

    @Column(name = "group_name")
    private String group;

    private BigDecimal price;
    public MenuItemResponsePayload(MenuItem menuItem) {

        this.id = id;
        this.menuId = menuItem.getMenuId();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.type = menuItem.getType();
        this.group = menuItem.getGroup();
        this.price = menuItem.getPrice();
    }
}
