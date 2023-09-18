package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v2.model.CuisineModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;


@ApiModel("CuisinesModel")
@Getter
@Setter
public class CuisinesModelOpenApiV2 {

    private CuisinesEmbeddedModelOpenApiV2 _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CuisinesEmbeddedModel")
    @Data
    public static class CuisinesEmbeddedModelOpenApiV2 {

        private List<CuisineModelV2> cuisines;

    }

}
