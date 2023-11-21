package com.example.studentManagement.repository;

import com.example.studentManagement.entity.Restaurant;
import com.example.studentManagement.responsePayloads.RestaurantCreateResponsePayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    @Query(value = "select New com.example.studentManagement.entity.RestaurantCreateResponsePayload(id, name, restaurant, type) from restaurant ",nativeQuery = true)
    public List<RestaurantCreateResponsePayload> findAllRestaurantCreateResponsePayload();

}
