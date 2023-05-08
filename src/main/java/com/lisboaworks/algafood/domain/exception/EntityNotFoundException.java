package com.lisboaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
