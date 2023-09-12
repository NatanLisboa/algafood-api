package com.lisboaworks.algafood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Links")
public class LinksModelOpenApi {

    private LinkModel rel;

    @Getter
    @Setter
    @ApiModel("Link")
    private static class LinkModel {

        private String href;
        private Boolean templated;

    }

}
