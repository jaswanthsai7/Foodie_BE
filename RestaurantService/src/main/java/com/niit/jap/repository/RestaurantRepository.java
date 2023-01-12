package com.niit.jap.repository;

import com.niit.jap.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant,String> {

    List<Restaurant> findRestaurantByCity(String cityName);
    @Query("{ 'id' : ?0 }")
    Optional<Restaurant> findById(String id);
}
