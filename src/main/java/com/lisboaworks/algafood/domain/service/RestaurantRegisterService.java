package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.RestaurantNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RestaurantRegisterService {

    private final RestaurantRepository restaurantRepository;
    private final CuisineRegisterService cuisineRegisterService;
    private final CityRegisterService cityRegisterService;
    private final PaymentMethodRegisterService paymentMethodRegisterService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        City city = cityRegisterService.findOrThrowException(cityId);

        restaurant.setCuisine(cuisine);
        restaurant.getAddress().setCity(city);

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

    @Transactional
    public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = this.findOrThrowException(restaurantId);
        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(paymentMethodId);

        restaurant.addPaymentMethod(paymentMethod);
    }

    @Transactional
    public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = this.findOrThrowException(restaurantId);
        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(paymentMethodId);

        restaurant.removePaymentMethod(paymentMethod);
    }

    public Restaurant findOrThrowException(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

}
