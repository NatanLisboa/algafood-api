package com.lisboaworks.algafood.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityId) {
        this(String.format("There is no city with id %d in database", cityId));
    }
}
