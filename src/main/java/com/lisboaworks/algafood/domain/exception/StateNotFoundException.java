package com.lisboaworks.algafood.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("There is no state with id %d in database", stateId));
    }

}
