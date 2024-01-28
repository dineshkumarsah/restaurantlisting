package com.codedecode.restaurantlisting.mapper;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;

public class RestaurantMapper {
    public  static RestuarantDTO matToRestaurantDTO(Restaurant restaurant){
     return new RestuarantDTO(
     restaurant.getId(),
             restaurant.getName(),
             restaurant.getAddress(),
             restaurant.getCity(),
             restaurant.getRestaurantDescription()
     );
    }
    public static Restaurant  mapToRestaurant(RestuarantDTO restuarantDTO){
        return new Restaurant(
                restuarantDTO.getId(),
                restuarantDTO.getName(),
                restuarantDTO.getAddress(),
                restuarantDTO.getCity(),
                restuarantDTO.getRestaurantDescription()
        );

    }
}
