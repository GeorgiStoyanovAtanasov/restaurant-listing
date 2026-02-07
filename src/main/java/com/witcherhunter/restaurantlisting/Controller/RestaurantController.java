package com.witcherhunter.restaurantlisting.Controller;

import com.witcherhunter.restaurantlisting.Dto.RestaurantDTO;
import com.witcherhunter.restaurantlisting.Service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@Slf4j
@CrossOrigin
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        log.info(restaurantDTO.getName());
        RestaurantDTO restaurantAdded = restaurantService.saveRestaurant(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Integer id){
        return restaurantService.getRestaurant(id);
    }
}
