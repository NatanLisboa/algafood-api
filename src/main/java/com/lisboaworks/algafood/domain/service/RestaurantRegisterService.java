package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CuisineRepository cuisineRepository;

    public Restaurant save(Restaurant restaurant) {

        Long cuisineId = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineRepository.findById(cuisineId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no cuisine register with id %d", cuisineId)));

        restaurant.setCuisine(cuisine);

        return restaurantRepository.save(restaurant);
    }

}
