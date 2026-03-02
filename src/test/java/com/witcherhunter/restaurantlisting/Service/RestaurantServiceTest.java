package com.witcherhunter.restaurantlisting.Service;

import com.witcherhunter.restaurantlisting.Dto.RestaurantDTO;
import com.witcherhunter.restaurantlisting.Entity.Restaurant;
import com.witcherhunter.restaurantlisting.Mapper.RestaurantMapper;
import com.witcherhunter.restaurantlisting.Repository.RestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Test
    void testFindAllRestaurants() {

        // Arrange
        Restaurant r1 = new Restaurant(1L, "R1", "Addr1", "City1", "Desc1");
        Restaurant r2 = new Restaurant(2L, "R2", "Addr2", "City2", "Desc2");

        List<Restaurant> restaurants = Arrays.asList(r1, r2);

        Mockito.when(restaurantRepository.findAll())
                .thenReturn(restaurants);

        // Act
        List<RestaurantDTO> result = restaurantService.findAllRestaurants();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("R1", result.get(0).getName());

        Mockito.verify(restaurantRepository).findAll();
    }

    @Test
    void testSaveRestaurant() {

        // Arrange
        RestaurantDTO dto =
                new RestaurantDTO(1L, "Pizza", "Addr", "City", "Desc");

        Restaurant entity =
                RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(dto);

        Mockito.when(restaurantRepository.save(Mockito.any(Restaurant.class)))
                .thenReturn(entity);

        // Act
        RestaurantDTO result = restaurantService.saveRestaurant(dto);

        // Assert
        Assertions.assertEquals(dto.getName(), result.getName());

        Mockito.verify(restaurantRepository)
                .save(Mockito.any(Restaurant.class));
    }

    @Test
    void testGetRestaurant_Found() {

        // Arrange
        Integer id = 1;

        Restaurant restaurant =
                new Restaurant(1L, "Burger", "Addr", "City", "Desc");

        Mockito.when(restaurantRepository.findById(id))
                .thenReturn(Optional.of(restaurant));

        // Act
        ResponseEntity<RestaurantDTO> response =
                restaurantService.getRestaurant(id);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Burger", response.getBody().getName());

        Mockito.verify(restaurantRepository).findById(id);
    }

    @Test
    void testGetRestaurant_NotFound() {

        // Arrange
        Integer id = 1;

        Mockito.when(restaurantRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<RestaurantDTO> response =
                restaurantService.getRestaurant(id);

        // Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        Mockito.verify(restaurantRepository).findById(id);
    }
}