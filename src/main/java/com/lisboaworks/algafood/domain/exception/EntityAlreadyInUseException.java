package com.lisboaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityAlreadyInUseException extends BusinessRuleException {

    public EntityAlreadyInUseException(String message) {
        super(message);
    }

}
