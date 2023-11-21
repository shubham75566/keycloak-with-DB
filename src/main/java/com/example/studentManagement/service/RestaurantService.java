package com.example.studentManagement.service;

import com.example.studentManagement.requestPayload.RestaurantCreateRequestPayload;
import com.example.studentManagement.responsePayloads.RestaurantCreateResponsePayload;

import java.util.List;

public interface RestaurantService {
    public RestaurantCreateResponsePayload createRestaurant(RestaurantCreateRequestPayload restaurantCreateRequestPayload);
    public List<RestaurantCreateResponsePayload> getRestaurants();
    public RestaurantCreateResponsePayload updateRestaurants(RestaurantCreateResponsePayload RestaurantCreateResponsePayload);
}
