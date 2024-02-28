package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    RestuarantDTO createRestaurant(RestuarantDTO restuarantDTO);
    List<RestuarantDTO> getRestaurantDTO();
    List<RestuarantDTO> getAllRestaurant();
   Optional<RestuarantDTO>  getRestaurantByid(Integer id);
   public void deleteById(Integer id);
   public void updateResturant(Integer id, RestuarantDTO restuarantDTO);
}
