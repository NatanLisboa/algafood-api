package com.lisboaworks.algafood.domain.exception;

public class EntityAlreadyInUseException extends BusinessRuleException {

    public EntityAlreadyInUseException(String message) {
        super(message);
    }

}
