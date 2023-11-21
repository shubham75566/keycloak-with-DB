package com.example.studentManagement.responsePayloads;

import com.example.studentManagement.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantCreateResponsePayload {
    public Long id;

    private String name;

    private String location;

    private String type;
    public RestaurantCreateResponsePayload(Restaurant restaurant) {

        this.id = restaurant.getId();
        this.location = restaurant.getLocation();
        this.type = restaurant.getType();
        this.name = restaurant.getName();

    }
}
