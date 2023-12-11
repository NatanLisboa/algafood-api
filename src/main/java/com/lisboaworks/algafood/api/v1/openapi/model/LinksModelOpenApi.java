package com.lisboaworks.algafood.api.v1.openapi.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksModelOpenApi {

    private LinkModel rel;

    @Getter
    @Setter
    private static class LinkModel {

        private String href;
        private Boolean templated;

    }

}
