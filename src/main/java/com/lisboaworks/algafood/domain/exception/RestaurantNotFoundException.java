package com.lisboaworks.algafood.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restaurantId) {
        super(String.format("There is no restaurant register with id %d", restaurantId));
    }

}
