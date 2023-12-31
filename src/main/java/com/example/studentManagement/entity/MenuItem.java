package com.example.studentManagement.entity;

import com.example.studentManagement.requestPayload.MenuItemRequestPayload;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Long menuId;

    private String name;

    private String description;

    @Column(name = "type_name")
    private String type;

    @Column(name = "group_name")
    private String group;

    private BigDecimal price;
    public MenuItem(MenuItemRequestPayload menuItem) {

        this.menuId = menuItem.getMenuId();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.type = menuItem.getType();
        this.group = menuItem.getGroup();
        this.price = menuItem.getPrice();

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getMenuId() {

        return menuId;
    }

    public void setMenuId(Long menuId) {

        this.menuId = menuId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getGroup() {

        return group;
    }

    public void setGroup(String group) {

        this.group = group;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

}
