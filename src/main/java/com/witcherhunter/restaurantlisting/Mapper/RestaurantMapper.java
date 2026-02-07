package com.witcherhunter.restaurantlisting.Mapper;

import com.witcherhunter.restaurantlisting.Dto.RestaurantDTO;
import com.witcherhunter.restaurantlisting.Entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
