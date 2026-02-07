package com.witcherhunter.restaurantlisting.Repository;

import com.witcherhunter.restaurantlisting.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
