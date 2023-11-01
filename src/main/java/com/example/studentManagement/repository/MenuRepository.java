package com.example.studentManagement.repository;

import com.example.studentManagement.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository  extends JpaRepository<Menu, Long>{

	Menu findByRestaurantId(Long restaurantId);
}
