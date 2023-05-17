package com.lisboaworks.algafood.domain.exception;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(String message, Throwable exceptionCause) {
        super(message, exceptionCause);
    }

}
