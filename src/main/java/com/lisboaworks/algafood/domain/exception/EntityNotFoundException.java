package com.lisboaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class EntityNotFoundException extends BusinessRuleException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
