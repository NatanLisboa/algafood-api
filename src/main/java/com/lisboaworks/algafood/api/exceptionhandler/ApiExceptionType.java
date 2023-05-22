package com.lisboaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiExceptionType {

    RESOURCE_NOT_FOUND("Resource not found", "/resource-not-found"),
    ENTITY_ALREADY_IN_USE("Entity already in use", "/entity-already-in-use"),
    BUSINESS_RULE_ERROR("Business rule error", "/business-rule-error"),
    INCOMPREHENSIBLE_MESSAGE("Incomprehensible message", "/incomprehensible-message"),
    INVALID_PARAMETER("Invalid parameter", "/invalid-parameter"),
    SYSTEM_ERROR("System error", "/system-error"),
    INVALID_DATA("Invalid data", "/invalid-data");

    private String title;
    private String uri;

    ApiExceptionType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }



}
