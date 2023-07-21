package com.lisboaworks.algafood.domain.exception;

public class ProductPhotoNotFoundException extends EntityNotFoundException {

    public ProductPhotoNotFoundException(String message) {
        super(message);
    }

    public ProductPhotoNotFoundException(Long restaurantId, Long productId) {
        this(String.format("There is no photo register for product with code %d" +
                " in restaurant with code %d", productId, restaurantId));
    }

}
