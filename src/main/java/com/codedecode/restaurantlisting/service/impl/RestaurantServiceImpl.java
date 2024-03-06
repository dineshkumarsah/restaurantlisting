package com.codedecode.restaurantlisting.service.impl;

import com.codedecode.restaurantlisting.Exception.RestaurantNotFound;
import com.codedecode.restaurantlisting.dto.NewRestaurantDTO;
import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.NewRestaurant;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantImgMapper;
import com.codedecode.restaurantlisting.mapper.RestaurantMapperInterface;
import com.codedecode.restaurantlisting.repo.NewRestaurantRepo;
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
    private final RestaurantImgMapper restaurantImgMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    NewRestaurantRepo newRestaurantRepo;

    @Autowired
    public RestaurantServiceImpl(
            RestaurantMapperInterface restaurantMapperInterface,
            RestaurantImgMapper restaurantImgMapper
    ) {
        this.restaurantMapperInterface = restaurantMapperInterface;
        this.restaurantImgMapper = restaurantImgMapper;

    }


    @Override
    public RestuarantDTO createRestaurant(RestuarantDTO restuarantDTO) {
        RestaurantMapperInterface mapper;
        Restaurant restaurant = restaurantMapperInterface.mapToRestaurant(restuarantDTO);
        Restaurant restaurantSave = restaurantRepository.save(restaurant);
        return restaurantMapperInterface.matToRestaurantDTO(restaurantSave);
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
    public Optional<RestuarantDTO> getRestaurantByid(Integer id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        return restaurantOptional.map(this::convertToDto);
    }
    @Override

    public Optional<NewRestaurantDTO> getRestaurantById(Integer id) throws RestaurantNotFound {
        Optional<NewRestaurant> restaurant = newRestaurantRepo.findById(id);
        try {
            if (restaurant.isPresent()) {
                return Optional.of(restaurantImgMapper.maToDtoOptional(restaurant));
            } else {
                throw new RestaurantNotFound("Restaurant not found by" + id);
            }
        } catch (Exception ex) {
            throw new RestaurantNotFound("Error while fetching the restaurant by the id");
        }
    }




    @Override
    public void updateResturant(Integer id, RestuarantDTO restuarantDTO) {
        Restaurant existRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        existRestaurant.setName(restuarantDTO.getName());
        existRestaurant.setAddress(restuarantDTO.getAddress());
        existRestaurant.setCity(restuarantDTO.getCity());
        existRestaurant.setRestaurantDescription(restuarantDTO.getRestaurantDescription());
        restaurantRepository.save(existRestaurant);
    }

    @Override
    public NewRestaurantDTO addRestaurant(NewRestaurantDTO dto) {
        NewRestaurant saveRestaurant = newRestaurantRepo.save(restaurantImgMapper.mapToNewRestaurant(dto));
        return restaurantImgMapper.mapToNewRestaurantDTO(saveRestaurant);
    }

    @Override
    public List<NewRestaurantDTO> getAllRestaurantWithImage() {
        List<NewRestaurant> newRestaurants = newRestaurantRepo.findAll();
        return convertToDTOS(newRestaurants);
    }

    @Override
    public void deleteRestaurant(Integer id) throws RestaurantNotFound {
        NewRestaurant restaurant = newRestaurantRepo.findById(id).orElseThrow(
                ()-> new RestaurantNotFound("Restaurant Not Found By the id "+ id)
        );
        NewRestaurant newRestaurant = newRestaurantRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        newRestaurantRepo.delete(restaurant);

    }

    @Override
    public NewRestaurantDTO updateNewRestaurant(Integer id, NewRestaurantDTO dto) throws RestaurantNotFound {
        NewRestaurant restaurant= newRestaurantRepo.findById(id).orElseThrow(()->new RuntimeException("Record not exit"));
        restaurant.setRestaurantImage(dto.getRestaurantImage());
        restaurant.setId(dto.getId());
        restaurant.setRestaurantDescription(dto.getRestaurantDescription());
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setCity(dto.getCity());
        NewRestaurant savedRest =   newRestaurantRepo.save(restaurant);
        return restaurantImgMapper.mapToNewRestaurantDTO(savedRest);
    }

    private List<NewRestaurantDTO> convertToDTOS(List<NewRestaurant> restaurants) {
        // Convert each entity to DTO and collect them into a list
        return restaurants.stream()
                .map(this::convertToDtoRestaurantWithImage)
                .collect(Collectors.toList());
    }

    private NewRestaurantDTO convertToDtoRestaurantWithImage(NewRestaurant restaurant) {
        return restaurantImgMapper.mapToNewRestaurantDTO(restaurant);
    }

    private RestuarantDTO convertToDto(Restaurant restaurant) {
        RestuarantDTO restuarantDTO = new RestuarantDTO();
        restuarantDTO.setId(restaurant.getId());
        restuarantDTO.setName(restaurant.getName());
        restuarantDTO.setAddress(restaurant.getAddress());
        restuarantDTO.setCity(restaurant.getCity());
        restuarantDTO.setRestaurantDescription(restaurant.getRestaurantDescription());
        return restuarantDTO;
    }
}
