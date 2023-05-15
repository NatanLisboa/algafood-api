package com.lisboaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiExceptionType {

    ENTITY_NOT_FOUND("Entity not found", "/entity-not-found");

    private String title;
    private String uri;

    ApiExceptionType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }



}
