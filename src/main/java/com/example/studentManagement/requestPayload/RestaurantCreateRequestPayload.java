package com.example.studentManagement.requestPayload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RestaurantCreateRequestPayload {


    private String name;

    private String location;

    private String type;

}
