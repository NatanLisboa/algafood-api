package com.lisboaworks.algafood.domain.exception;

public abstract class EntityNotFoundException extends BusinessRuleException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
