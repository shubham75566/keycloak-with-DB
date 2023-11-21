package com.example.studentManagement.repository;
import com.example.studentManagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

//	@Query(value = "SELECT o.id, o.restaurant_id, o.total, i.* FROM hotel.order o JOIN order_item i ON o.id = i.order_id;",nativeQuery = true)
	List<Order> findByRestaurantId(Long restaurantId);

}
