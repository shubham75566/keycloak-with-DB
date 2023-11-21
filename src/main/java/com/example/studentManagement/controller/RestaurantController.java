package com.example.studentManagement.controller;

import com.example.studentManagement.repository.MenuItemRepository;
import com.example.studentManagement.repository.MenuRepository;
import com.example.studentManagement.repository.RestaurantRepository;
import com.example.studentManagement.requestPayload.MenuRequestPayload;
import com.example.studentManagement.requestPayload.RestaurantCreateRequestPayload;
import com.example.studentManagement.responsePayloads.MenuItemResponsePayload;
import com.example.studentManagement.responsePayloads.MenuResponsePayload;
import com.example.studentManagement.responsePayloads.RestaurantCreateResponsePayload;
import com.example.studentManagement.service.RestaurantService;
import com.example.studentManagement.service.impl.MenuServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@SecurityRequirement(name = "keycloak")
public class RestaurantController {

    Logger log = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    MenuServiceImpl menuService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping
    @RequestMapping("/public/list")
    //Public API
    public ResponseEntity<List<RestaurantCreateResponsePayload>> getRestaurants() {

        try {
            log.debug("get all restaurant");
            return new ResponseEntity<>(restaurantService.getRestaurants(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurants ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("/public/menu/{restaurantId}")
    public ResponseEntity<MenuResponsePayload> getMenu(@PathVariable Long restaurantId) {

        try {
            log.debug("get menu method call with restaurant id : {}", restaurantId);
            return new ResponseEntity<>(menuService.getMenu(restaurantId), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while getting restaurant by id where restaurantId : {}", restaurantId, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<RestaurantCreateResponsePayload> createRestaurant(@RequestBody RestaurantCreateRequestPayload restaurantCreateRequestPayload) {

        try {
            log.debug("create restaurant controller with request payload : {}", restaurantCreateRequestPayload);
            return new ResponseEntity<>(restaurantService.createRestaurant(restaurantCreateRequestPayload), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while creating restaurant ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<RestaurantCreateResponsePayload> updateRestaurant(@RequestBody RestaurantCreateResponsePayload restaurantCreateResponsePayload) {

        try {
            log.debug("update restaurant controller with request payload :{}", restaurantCreateResponsePayload);
            return new ResponseEntity<>(restaurantService.updateRestaurants(restaurantCreateResponsePayload), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while updating restaurant by id where restaurantId : {}", restaurantCreateResponsePayload.getId(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @RequestMapping("/menu")
    public ResponseEntity<String> createMenu(@RequestBody MenuRequestPayload menu) {

        try {
            log.debug("create menu controller with menu request payload : {}", menu);
            menuService.createMenu(menu);
            return new ResponseEntity<>("create menu successful", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while creating menu ", e);
            return new ResponseEntity<>("failed to create menu successful", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    @RequestMapping("/menu/item/{itemId}/{price}")
    public ResponseEntity<MenuItemResponsePayload> updateMenuItemPrice(@PathVariable Long itemId, @PathVariable BigDecimal price) {

        try {
            log.debug("update menu item price controller with item id :{} and price : {}", itemId, price);
            return new ResponseEntity<>(menuService.updateMenuItemPrice(itemId, price), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurs while updating menu item price where menu item id : {}", itemId, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
