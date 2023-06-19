package com.lisboaworks.algafood.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("There is no product with code %d in restaurant with code %d", productId, restaurantId));
    }

}
