package com.lisboaworks.algafood.domain.exception;

public class EntityAlreadyInUseException extends RuntimeException {

    public EntityAlreadyInUseException(String message) {
        super(message);
    }

}
