package com.codedecode.restaurantlisting.mapper;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class DefaultRestaurantMapper implements RestaurantMapperInterface{
    public Restaurant mapToRestaurant(RestuarantDTO restuarantDTO) {
        return new Restaurant(
                restuarantDTO.getId(),
                restuarantDTO.getName(),
                restuarantDTO.getAddress(),
                restuarantDTO.getCity(),
                restuarantDTO.getRestaurantDescription()
        );
    }

    public   RestuarantDTO matToRestaurantDTO(Restaurant restaurant){
        return new RestuarantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getCity(),
                restaurant.getRestaurantDescription()
        );
    }
}
