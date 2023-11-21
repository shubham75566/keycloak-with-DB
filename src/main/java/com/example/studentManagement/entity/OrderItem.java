package com.example.studentManagement.entity;

import com.example.studentManagement.requestPayload.OrderItemRequestPayload;
import com.example.studentManagement.responsePayloads.OrderItemResponseItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class OrderItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Long orderId;

    private Long menuItemId;

    private BigDecimal price;
    public OrderItem() {

    }
    public OrderItem(OrderItemRequestPayload o) {
		this.menuItemId=o.getMenuItemId();
		this.price=o.getPrice();

	}
	public OrderItem(OrderItemRequestPayload orderItem, Long id) {
		this.menuItemId=orderItem.getMenuItemId();
		this.price= orderItem.getPrice();
		this.orderId=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
}
