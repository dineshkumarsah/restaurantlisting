package com.codedecode.restaurantlisting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestuarantDTO {
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
