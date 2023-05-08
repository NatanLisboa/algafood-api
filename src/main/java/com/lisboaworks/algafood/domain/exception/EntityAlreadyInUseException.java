package com.lisboaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyInUseException extends RuntimeException {

    public EntityAlreadyInUseException(String message) {
        super(message);
    }

}
