package com.example.studentManagement.entity;

import com.example.studentManagement.requestPayload.OrderItemRequestPayload;
import com.example.studentManagement.requestPayload.OrderRequestPayload;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Long restaurantId;

    private BigDecimal total;

    @OneToMany
    @Transient
	private List<OrderItem> orderItems;
    public Order() {

    }
    public Order(OrderRequestPayload orderRequestPayload) {
		this.restaurantId = orderRequestPayload.getRestaurantId();
		this.total = orderRequestPayload.getTotal();
		this.orderItems = orderRequestPayload.getOrderItems().stream().map(OrderItem::new).toList();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
