package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.Exception.RestaurantNotFound;
import com.codedecode.restaurantlisting.dto.NewRestaurantDTO;
import com.codedecode.restaurantlisting.dto.RestuarantDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    RestuarantDTO createRestaurant(RestuarantDTO restuarantDTO);
    List<RestuarantDTO> getRestaurantDTO();
    List<RestuarantDTO> getAllRestaurant();
   Optional<RestuarantDTO>  getRestaurantByid(Integer id);

    Optional<NewRestaurantDTO> getRestaurantById(Integer id) throws RestaurantNotFound;


   public void updateResturant(Integer id, RestuarantDTO restuarantDTO);

    NewRestaurantDTO addRestaurant(NewRestaurantDTO dto);

  List<NewRestaurantDTO>   getAllRestaurantWithImage();

    void deleteRestaurant(Integer id);
    NewRestaurantDTO updateNewRestaurant(Integer id, NewRestaurantDTO dto);
}
