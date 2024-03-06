package com.codedecode.restaurantlisting.mapper;

import com.codedecode.restaurantlisting.dto.NewRestaurantDTO;
import com.codedecode.restaurantlisting.entity.NewRestaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface RestaurantImgMapper {

    NewRestaurantDTO mapToNewRestaurantDTO(NewRestaurant restaurant);
    NewRestaurant mapToNewRestaurant(NewRestaurantDTO dto);
    NewRestaurantDTO maToDtoOptional(Optional<NewRestaurant>restaurant);


}
