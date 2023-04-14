package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> queryByShippingFeeBetween(BigDecimal startFee, BigDecimal endFee);

    List<Restaurant> findByNameContainingAndCuisineId(String restaurantName, Long cuisineId); // Find by cuisine id and restaurant name with like (in that order)

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name); // Only the first one

    List<Restaurant> findTop2ByNameContaining(String name); // The first two

    int countByCuisineId(Long cuisineId);
}
