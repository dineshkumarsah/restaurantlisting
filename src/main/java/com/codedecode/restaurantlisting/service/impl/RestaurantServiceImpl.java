package com.codedecode.restaurantlisting.service.impl;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapperInterface;
import com.codedecode.restaurantlisting.repo.RestaurantRepository;
import com.codedecode.restaurantlisting.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapperInterface restaurantMapperInterface;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(
            RestaurantMapperInterface restaurantMapperInterface
    ){
      this.restaurantMapperInterface = restaurantMapperInterface;

    }


    @Override
    public RestuarantDTO createRestaurant(RestuarantDTO restuarantDTO) {
        RestaurantMapperInterface mapper ;
       Restaurant restaurant= restaurantMapperInterface.mapToRestaurant(restuarantDTO);
        Restaurant restaurantSave = restaurantRepository.save(restaurant);
        return  restaurantMapperInterface.matToRestaurantDTO(restaurantSave);
    }
    @Override
    public List<RestuarantDTO> getRestaurantDTO() {
        return null;
    }

    @Override
    public List<RestuarantDTO> getAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
       return restaurants.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RestuarantDTO>  getRestaurantByid(Integer id) {
      Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        return restaurantOptional.map(this::convertToDto);
    }

    @Override
    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public void updateResturant(Integer id, RestuarantDTO restuarantDTO) {
        Restaurant existRestaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Restaurant not found"));
        existRestaurant.setName(restuarantDTO.getName());
        existRestaurant.setAddress(restuarantDTO.getAddress());
        existRestaurant.setCity(restuarantDTO.getCity());
        existRestaurant.setRestaurantDescription(restuarantDTO.getRestaurantDescription());
        restaurantRepository.save(existRestaurant);
    }

    private RestuarantDTO convertToDto(Restaurant restaurant){
        RestuarantDTO restuarantDTO = new RestuarantDTO();
        restuarantDTO.setId(restaurant.getId());
        restuarantDTO.setName(restaurant.getName());
        restuarantDTO.setAddress(restaurant.getAddress());
        restuarantDTO.setCity(restaurant.getCity());
        restuarantDTO.setRestaurantDescription(restaurant.getRestaurantDescription());
        return restuarantDTO;
    }
}
