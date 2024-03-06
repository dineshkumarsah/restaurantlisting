package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.Exception.RestaurantNotFound;
import com.codedecode.restaurantlisting.dto.NewRestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {
    private RestaurantService restaurantService;
    @PostMapping(value = "/addRestaurant", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NewRestaurantDTO> addRestaurant(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("restaurantDescription") String restaurantDescription,
            @RequestParam("city") String city,
            @RequestParam("address") String address) throws IOException {

        NewRestaurantDTO dto = new NewRestaurantDTO();
        dto.setRestaurantDescription(restaurantDescription);
        dto.setFileName(file.getOriginalFilename());
        dto.setCity(city);
        dto.setName(name);
        dto.setAddress(address);
        dto.setRestaurantImage(file.getBytes());
        NewRestaurantDTO restaurantDTO = restaurantService.addRestaurant(dto);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.CREATED);
    }

    @GetMapping("/restaurantImg")
    public ResponseEntity<List<NewRestaurantDTO>> getRestaurantListImage() {
        List<NewRestaurantDTO> restaurantDTO = restaurantService.getAllRestaurantWithImage();
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);

    }

    @GetMapping("/restaurantImg/{id}")
    public ResponseEntity<NewRestaurantDTO> getRestaurantByIds(@PathVariable Integer id) {
        try {
            Optional<NewRestaurantDTO> restaurantDTO = restaurantService.getRestaurantById(id);
            if (restaurantDTO.isPresent()) {
                return ResponseEntity.ok(restaurantDTO.get());
            } else {
                return ResponseEntity.ok("not found").notFound().build();
            }
        } catch (RestaurantNotFound ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @DeleteMapping("/restaurantImg/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
        try {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.noContent().build();
        } catch (RestaurantNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/updateRestaurant/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NewRestaurantDTO> updateNewRestaurant( @RequestParam("file") MultipartFile file,
                                                                 @RequestParam("name") String name,
                                                                 @RequestParam("restaurantDescription") String restaurantDescription,
                                                                 @RequestParam("city") String city,
                                                                 @RequestParam("address") String address,
                                                                 @PathVariable Integer id) throws IOException{

          NewRestaurantDTO dto1=new NewRestaurantDTO();
          dto1.setName(name);
          dto1.setAddress(address);
          dto1.setCity(city);
          dto1.setRestaurantDescription(restaurantDescription);
          dto1.setId(id);
          dto1.setRestaurantImage(file.getBytes());
            restaurantService.updateNewRestaurant(id,dto1);
          return new ResponseEntity<>(dto1,HttpStatus.OK);

    }


}
