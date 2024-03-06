package com.codedecode.restaurantlisting.repo;

import com.codedecode.restaurantlisting.entity.NewRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRestaurantRepo extends JpaRepository<NewRestaurant,Integer> {
}
