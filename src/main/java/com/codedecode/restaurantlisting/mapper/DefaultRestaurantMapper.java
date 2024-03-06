package com.codedecode.restaurantlisting.mapper;

import com.codedecode.restaurantlisting.Exception.RestaurantNotFound;
import com.codedecode.restaurantlisting.dto.NewRestaurantDTO;
import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.NewRestaurant;
import com.codedecode.restaurantlisting.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultRestaurantMapper implements RestaurantMapperInterface,RestaurantImgMapper{
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

    @Override
    public NewRestaurantDTO mapToNewRestaurantDTO(NewRestaurant restaurant) {

        return new NewRestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getCity(),
                restaurant.getRestaurantDescription(),
                restaurant.getFileName(),
                restaurant.getRestaurantImage()

        );
    }

    @Override
    public NewRestaurant mapToNewRestaurant(NewRestaurantDTO dto) {
        return new NewRestaurant(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getCity(),
                dto.getRestaurantDescription(),
                dto.getFileName(),
                dto.getRestaurantImage()

        );
    }

    @Override
    public NewRestaurantDTO maToDtoOptional(Optional<NewRestaurant> restaurant) {
        if(restaurant.isPresent()){
            return new NewRestaurantDTO(
                    restaurant.get().getId(),
                    restaurant.get().getName(),
                    restaurant.get().getAddress(),
                    restaurant.get().getCity(),
                    restaurant.get().getRestaurantDescription(),
                    restaurant.get().getFileName(),
                    restaurant.get().getRestaurantImage()
            );
        }else {
          throw new RestaurantNotFound("Restaurant not found");
        }

    }
}
