package com.codedecode.restaurantlisting.mapper;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;

public interface RestaurantMapperInterface {
    Restaurant mapToRestaurant(RestuarantDTO restuarantDTO);
    RestuarantDTO matToRestaurantDTO(Restaurant restaurant);
}
