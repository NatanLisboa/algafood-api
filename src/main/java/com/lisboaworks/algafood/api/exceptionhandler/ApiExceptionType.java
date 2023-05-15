package com.lisboaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiExceptionType {

    ENTITY_NOT_FOUND("Entity not found", "/entity-not-found"),
    ENTITY_ALREADY_IN_USE("Entity already in use", "/entity-already-in-use"),
    BUSINESS_RULE_ERROR("Business rule error", "/business-rule-error"),

    INCOMPREHENSIBLE_MESSAGE("Incomprehensible message", "/incomprehensible-message");

    private String title;
    private String uri;

    ApiExceptionType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }



}
