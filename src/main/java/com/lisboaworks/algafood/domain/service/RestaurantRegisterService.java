package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.RestaurantNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantRegisterService {
    
	public static final String RESTAURANT_NOT_FOUND_MESSAGE = "There is no restaurant register with id %d";
 
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private CuisineRegisterService cuisineRegisterService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        restaurant.setCuisine(cuisine);

        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void activate(Long restaurantId) {
        Restaurant restaurant = this.findOrThrowException(restaurantId);
        restaurant.activate();
    }

    @Transactional
    public void inactivate(Long restaurantId) {
        Restaurant restaurant = this.findOrThrowException(restaurantId);
        restaurant.inactivate();
    }

    public Restaurant findOrThrowException(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

}
