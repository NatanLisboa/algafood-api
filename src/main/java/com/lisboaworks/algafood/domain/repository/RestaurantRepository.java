package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByShippingFeeBetween(BigDecimal startFee, BigDecimal endFee);

    List<Restaurant> findByNameContainingAndCuisineId(String restaurantName, Long cuisineId); // Find by cuisine id and restaurant name with like (in that order)
}
