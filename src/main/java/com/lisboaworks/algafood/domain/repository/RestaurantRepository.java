package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, CustomizedRestaurantRepository, JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> queryByShippingFeeBetween(BigDecimal startFee, BigDecimal endFee);

    List<Restaurant> retrieveByNameAndCuisineId(String name, @Param("id") Long cuisineId);
    //List<Restaurant> findByNameContainingAndCuisineId(String restaurantName, Long cuisineId); // Find by cuisine id and restaurant name with like (in that order)

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name); // Only the first one

    List<Restaurant> findTop2ByNameContaining(String name); // The first two

    int countByCuisineId(Long cuisineId);
}
