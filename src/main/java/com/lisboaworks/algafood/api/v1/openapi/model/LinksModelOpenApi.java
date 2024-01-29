package com.lisboaworks.algafood.api.v1.openapi.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksModelOpenApi {

    private LinkModel rel;

    @Getter
    @Setter
    private static class LinkModel {

        @Schema(example = "http://localhost:8080/v1")
        private String href;

        @Schema(example = "true")
        private Boolean templated;

    }

}
