package com.example.studentManagement.controller;

import com.example.studentManagement.entity.Menu;
import com.example.studentManagement.entity.MenuItem;
import com.example.studentManagement.entity.Restaurant;
import com.example.studentManagement.repository.MenuItemRepository;
import com.example.studentManagement.repository.MenuRepository;
import com.example.studentManagement.repository.RestaurantRepository;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
@SecurityRequirement(name = "keycloak")
public class RestaurantController {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MenuItemRepository menuItemRepository;

	@GetMapping
	@RequestMapping("/public/list")
	//Public API
	public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
	
	@GetMapping
	@RequestMapping("/public/menu/{restaurantId}")
	//Public API
	public Menu getMenu(@PathVariable Long restaurantId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        menu.setMenuItems(menuItemRepository.findAllByMenuId(menu.id));
        return menu;
    }


	@PostMapping
	// admin can access (admin)
	public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
	
	@PutMapping
//	@PreAuthorize("hasRole('client_manager')")
	// manager can access (suresh)
	public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
	
	@PostMapping
	@RequestMapping("/menu")
//	@PreAuthorize("hasRole('client_manager')")
	// manager can access (suresh)
	public Menu createMenu(@RequestBody Menu menu) {
		menuRepository.save(menu);
        menu.getMenuItems().forEach(menuItem -> {
            menuItem.setMenuId(menu.id);
            menuItemRepository.save(menuItem);
        });
        return menu;
    }
	
	@PutMapping
	@RequestMapping("/menu/item/{itemId}/{price}")
//	@PreAuthorize("hasRole('client_owner')")
	// owner can access (amar)
	public MenuItem updateMenuItemPrice(@PathVariable Long itemId
            , @PathVariable BigDecimal price) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(itemId);
        menuItem.get().setPrice(price);
        menuItemRepository.save(menuItem.get());
        return menuItem.get();
    }

}
