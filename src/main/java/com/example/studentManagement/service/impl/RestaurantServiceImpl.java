package com.example.studentManagement.service.impl;

import com.example.studentManagement.entity.Restaurant;
import com.example.studentManagement.repository.MenuItemRepository;
import com.example.studentManagement.repository.MenuRepository;
import com.example.studentManagement.repository.RestaurantRepository;
import com.example.studentManagement.requestPayload.RestaurantCreateRequestPayload;
import com.example.studentManagement.responsePayloads.RestaurantCreateResponsePayload;
import com.example.studentManagement.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public RestaurantCreateResponsePayload createRestaurant(RestaurantCreateRequestPayload restaurantCreateRequestPayload) {
        try {
            return new RestaurantCreateResponsePayload(restaurantRepository.save(new Restaurant(restaurantCreateRequestPayload)));
        }catch (Exception e){
            log.error("Exception occurs while creating new restaurant ",e);
            throw new RuntimeException();
        }
    }
    @Override
    public List<RestaurantCreateResponsePayload> getRestaurants() {
        return restaurantRepository.findAllRestaurantCreateResponsePayload();
    }
    @Override
    public RestaurantCreateResponsePayload updateRestaurants(RestaurantCreateResponsePayload restaurantCreateResponsePayload) {
        try {
            Optional<Restaurant> restaurant=restaurantRepository.findById(restaurantCreateResponsePayload.getId());
            if (restaurant.isEmpty()){
                throw new RuntimeException("Restaurant not fount with id "+restaurantCreateResponsePayload.id);
            }
            return new RestaurantCreateResponsePayload(restaurantRepository.save(new Restaurant(restaurantCreateResponsePayload)));
        }catch (Exception e){
            log.error("Exception occurs while creating new restaurant ",e);
            throw new RuntimeException();
        }
    }
}
