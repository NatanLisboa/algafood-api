package com.lisboaworks.algafood.domain.exception;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    public PaymentMethodNotFoundException(Long paymentMethodId) {
        this(String.format("There is no payment method with id %d in database", paymentMethodId));
    }
}
