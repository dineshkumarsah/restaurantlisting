package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.mapper.RestaurantMapperInterface;
import com.codedecode.restaurantlisting.repo.RestaurantRepository;
import com.codedecode.restaurantlisting.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepository;
    @Mock
    RestaurantMapperInterface restaurantMapperInterface;
    @InjectMocks
    RestaurantServiceImpl restaurantService;

    @BeforeEach
    public void setUp(){
        //in order to Mock and InjectMock annotation to take effect,
        //you need to call MockitoAnnotations.openMocks(this)
        MockitoAnnotations.openMocks(this);
    }
    @Test
   public void testgetAllRestaurant(){
       //create a simple restaurant entity
        Restaurant restaurant =new Restaurant();
        restaurant.setId(1);
        restaurant.setName("restaurant 1");
        Restaurant restaurant2 =new Restaurant();
        restaurant2.setId(2);
        restaurant2.setName("restaurant 2");

        //mock the behaviour of the restaurantRepository.findAll() method
        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant,restaurant2));

        List<RestuarantDTO> restuarantDTOS = restaurantService.getAllRestaurant();

        assertEquals(2,restuarantDTOS.size());

        assertEquals("restaurant 1",restuarantDTOS.get(0).getName());
        assertEquals("restaurant 2",restuarantDTOS.get(1).getName());
    }

    @Test
    void testCreateRestaurant() {
        RestuarantDTO inputDTO = new RestuarantDTO();
        Restaurant inputRestaurant = new Restaurant();
        when(restaurantMapperInterface.mapToRestaurant(inputDTO)).thenReturn(inputRestaurant);
        when(restaurantMapperInterface.matToRestaurantDTO(any(Restaurant.class))).thenReturn(inputDTO);
        when(restaurantRepository.save(inputRestaurant)).thenReturn(inputRestaurant);

        RestuarantDTO resultDTO = restaurantService.createRestaurant(inputDTO);

        assertEquals(inputDTO, resultDTO);
        verify(restaurantMapperInterface, times(1)).mapToRestaurant(inputDTO);
        verify(restaurantRepository, times(1)).save(inputRestaurant);
    }
    @Test
    void testGetRestaurantByid(){
        //Crate dummy restaurant
      Restaurant restaurant = new Restaurant();
      restaurant.setName("Restaurant 1");
      restaurant.setId(1);

      //Mock the behavior findById()
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));


      //call the method user test
        Optional<RestuarantDTO> restuarantDTO = restaurantService.getRestaurantByid(1);

        //verify that correct DTO recturn
        assertEquals("Restaurant 1",restuarantDTO.get().getName());

        verify(restaurantRepository, times(1)).findById(1);
    }

    @Test
    public  void testDeleteById(){
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant 1");
        restaurantRepository.save(restaurant);

        Integer id = restaurant.getId();
        restaurantRepository.deleteById(id);
        assertFalse(restaurantRepository.existsById(id));

    }

    @Test
    public void testUpdateResturant(){

        //Mock data
        Integer id = 1;
        RestuarantDTO restuarantDTO = new RestuarantDTO();
        restuarantDTO.setName("Updated restaurant");
        restuarantDTO.setAddress("Updated address");
        restuarantDTO.setCity("Updated city");
        restuarantDTO.setRestaurantDescription("Updated description");

        //Mock existing restaurant
        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setId(id);
        existingRestaurant.setName("Old Restaurant");
        existingRestaurant.setAddress("Old Address");
        existingRestaurant.setCity("Old City");
        existingRestaurant.setRestaurantDescription("Old Description");

        //mock repository behavior
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(existingRestaurant));
        //call the update method
        restaurantService.updateResturant(id,restuarantDTO);

        //verify that the repository's save method was called with updated restaurant
        verify(restaurantRepository).save(existingRestaurant);

        //verify theat restaurant value are updated
        assertEquals("Updated restaurant",existingRestaurant.getName());
        assertEquals("Updated address", existingRestaurant.getAddress());
        assertEquals("Updated city",existingRestaurant.getCity());
        assertEquals("Updated description",existingRestaurant.getRestaurantDescription());

    }

}