package com.witcherhunter.restaurantlisting.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
