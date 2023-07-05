package com.lisboaworks.algafood.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String orderCode) {
        super(String.format("There is no order registered with code '%s'", orderCode));
    }

}
