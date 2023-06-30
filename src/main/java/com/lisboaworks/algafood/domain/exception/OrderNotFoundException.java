package com.lisboaworks.algafood.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        this(String.format("There is no order registered with code %d", orderId));
    }
    
}
