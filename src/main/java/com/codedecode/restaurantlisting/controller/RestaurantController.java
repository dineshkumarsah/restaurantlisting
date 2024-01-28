package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestuarantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
private RestaurantService restaurantService;
@PostMapping
public ResponseEntity<RestuarantDTO> createRestaurant(@RequestBody RestuarantDTO restuarantDTO){
    RestuarantDTO saveRestaurantDTO = restaurantService.createRestaurant(restuarantDTO);
    return new ResponseEntity<>(saveRestaurantDTO, HttpStatus.CREATED);
}
@GetMapping
public List<RestuarantDTO> getAllRestaurant(){
    return restaurantService.getAllRestaurant();
}
@GetMapping("/{id}")
public ResponseEntity<RestuarantDTO> getRestaurantById(@PathVariable Integer id){
    return   restaurantService.getRestaurantByid(id).
            map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}
@DeleteMapping("/{id}")
public void deleteById(@PathVariable Integer id){
    restaurantService.deleteById(id);
}
@PutMapping("/{id}")
public void updateRestaurant(@PathVariable Integer id, @RequestBody RestuarantDTO restuarantDTO){
    restaurantService.updateResturant(id, restuarantDTO);
}
}