package com.lisboaworks.algafood.domain.exception;

public class CuisineNotFoundException extends EntityNotFoundException {

    public CuisineNotFoundException(String message) {
        super(message);
    }

    public CuisineNotFoundException(Long cuisineId) {
        super(String.format("Cuisine entity with id %d could not be found in database", cuisineId));
    }

}
