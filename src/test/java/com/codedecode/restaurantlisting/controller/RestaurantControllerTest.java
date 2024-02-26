package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {
    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    public void setUp(){
        //in order to Mock and InjectMock annotation to take effect,
        //you need to call MockitoAnnotations.openMocks(this)
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testgetAllRestaurant(){
        //Mock the service behaviour
        List<RestuarantDTO> mockRestaurants= Arrays.asList(
                new RestuarantDTO(1, "Restaurant 1","Address 1","city 1", "Description 1"),
                new RestuarantDTO(2, "Restaurant 2","Address 2","city 2", "Description 2")

        );
        when(restaurantService.getAllRestaurant()).thenReturn(mockRestaurants);

        //call the controller method
      ResponseEntity<List<RestuarantDTO> > restuarantDTOS = restaurantController.getAllRestaurant();

       //verify the response
        assertEquals(HttpStatus.OK, restuarantDTOS.getStatusCode());
        assertEquals(mockRestaurants,restuarantDTOS.getBody());

        //Verify that the service method was called
        verify(restaurantService, times(1)).getAllRestaurant();

    }
    @Test

   public void testgetRestaurantById(){

        //create a mock restaurant id
       Integer mockRestaurantId = 1;

       //create a mockrestaurant to be returned by the service
       RestuarantDTO mockRestaurant = new RestuarantDTO(1, "Restaurant 1","Address 1","city 1", "Description 1");

       //mock the service behavior
       when(restaurantService.getRestaurantByid(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant) );

       //call the controller methos
       ResponseEntity<RestuarantDTO> response = restaurantController.getRestaurantById(mockRestaurantId);
       //verify the response
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertEquals(mockRestaurant, response.getBody());

       //verify that service method was called
       verify(restaurantService,times(1)).getRestaurantByid(mockRestaurantId);

   }
   @Test
    public void testcreateRestaurant(){
       RestuarantDTO mockRestaurantDTO = new RestuarantDTO();
       mockRestaurantDTO.setName("restaurant 1");
       mockRestaurantDTO.setRestaurantDescription("description 1");

       //assume your service return the saved restaurantDto
       when(restaurantService.createRestaurant(any())).thenReturn(mockRestaurantDTO);

       //call the createRestaurant method of the controller
       ResponseEntity<RestuarantDTO> response = restaurantController.createRestaurant(mockRestaurantDTO);

       //Assert that the response status code is httpStatus.CREATE (201)
       assertEquals(HttpStatus.CREATED, response.getStatusCode());

       //Assert that returned DTO matched the expected DTO
       assertEquals(mockRestaurantDTO,response.getBody());

       //verify that thecreatedRestaurant method of the service is called with correct argument
       verify(restaurantService,times(1)).createRestaurant(mockRestaurantDTO);

   }
   @Test
   public void testdeleteById(){
        //Define a semple id for testing
       Integer id = 1;

       //Call the deleteById method of the controller
       restaurantController.deleteById(id);

       //verify that deletebyid method of service is called with the correct argument
       verify(restaurantService, times(1)).deleteById(id);

   }

   @Test
   public void testupdateRestaurant(){
        Integer id=1;
        RestuarantDTO dto = new RestuarantDTO();
        dto.setName("Restaurant 2");
        restaurantController.updateRestaurant(id,dto);
        verify(restaurantService, times(1)).updateResturant(id,dto);

   }
}
