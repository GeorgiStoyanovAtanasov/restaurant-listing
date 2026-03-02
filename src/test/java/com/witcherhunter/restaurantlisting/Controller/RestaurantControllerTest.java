package com.witcherhunter.restaurantlisting.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.LongValue;
import com.witcherhunter.restaurantlisting.Dto.RestaurantDTO;
import com.witcherhunter.restaurantlisting.Service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFetchAllRestaurants(){
        //Arrange
        List<RestaurantDTO> restaurantDTOList = Arrays.asList(
                new RestaurantDTO(1L, "Restaurant 1", "Address 1", "City 1", "Description 1"),
                new RestaurantDTO(2L, "Restaurant 2", "Address 2", "City 2", "Description 2")
        );
        Mockito.when(restaurantService.findAllRestaurants()).thenReturn(restaurantDTOList);

        //Act
        ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurants();

        //Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), restaurantDTOList);
        Mockito.verify(restaurantService, Mockito.times(1)).findAllRestaurants();
    }

    @Test
    void testSaveRestaurant() {

        // Arrange
        RestaurantDTO requestDTO =
                new RestaurantDTO(1L, "Pizza House", "Address", "City", "Nice food");

        RestaurantDTO savedDTO =
                new RestaurantDTO(1L, "Pizza House", "Address", "City", "Nice food");

        Mockito.when(restaurantService.saveRestaurant(requestDTO))
                .thenReturn(savedDTO);

        // Act
        ResponseEntity<RestaurantDTO> response =
                restaurantController.saveRestaurant(requestDTO);

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(savedDTO, response.getBody());

        Mockito.verify(restaurantService, Mockito.times(1))
                .saveRestaurant(requestDTO);
    }

    @Test
    void testFetchRestaurantById() {

        // Arrange
        Integer id = 1;

        RestaurantDTO restaurantDTO =
                new RestaurantDTO(1L, "Burger Place", "Address", "City", "Description");

        ResponseEntity<RestaurantDTO> serviceResponse =
                new ResponseEntity<>(restaurantDTO, HttpStatus.OK);

        Mockito.when(restaurantService.getRestaurant(id))
                .thenReturn(serviceResponse);

        // Act
        ResponseEntity<RestaurantDTO> response =
                restaurantController.fetchRestaurantById(id);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(restaurantDTO, response.getBody());

        Mockito.verify(restaurantService, Mockito.times(1))
                .getRestaurant(id);
    }
}
