package com.example.studentManagement.entity;

import com.example.studentManagement.requestPayload.RestaurantCreateRequestPayload;
import com.example.studentManagement.responsePayloads.RestaurantCreateResponsePayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
    private String name;

    private String location;

    @Column(name = "type_name")
    private String type;

    public Restaurant(RestaurantCreateRequestPayload restaurantCreateRequestPayload) {
        this.location=restaurantCreateRequestPayload.getLocation();
        this.name= restaurantCreateRequestPayload.getName();
        this.type= restaurantCreateRequestPayload.getType();
    }
    public Restaurant(RestaurantCreateResponsePayload restaurantCreateRequestPayload) {
        this.location=restaurantCreateRequestPayload.getLocation();
        this.name= restaurantCreateRequestPayload.getName();
        this.type= restaurantCreateRequestPayload.getType();
        this.id= restaurantCreateRequestPayload.getId();
    }
}
