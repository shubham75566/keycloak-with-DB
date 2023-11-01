package com.example.studentManagement.repository;
import com.example.studentManagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByRestaurantId(Long restaurantId);

}
