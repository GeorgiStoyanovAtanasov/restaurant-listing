package com.witcherhunter.restaurantlisting.Service;

import com.witcherhunter.restaurantlisting.Dto.RestaurantDTO;
import com.witcherhunter.restaurantlisting.Entity.Restaurant;
import com.witcherhunter.restaurantlisting.Mapper.RestaurantMapper;
import com.witcherhunter.restaurantlisting.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> findAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        //map to dto
        List<RestaurantDTO> restaurantDTOS = restaurants.stream()
                .map(restaurant ->
                        RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)
                )
                .collect(Collectors.toList());
        return restaurantDTOS;
    }
    public RestaurantDTO saveRestaurant(RestaurantDTO restaurantDTO){
        Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);
        restaurantRepository.save(restaurant);
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant);
    }

    public ResponseEntity<RestaurantDTO> getRestaurant(Integer id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
