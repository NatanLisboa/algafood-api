package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;


@ApiModel("CuisinesModel")
@Getter
@Setter
public class CuisinesModelOpenApi {

    private CuisinesEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CuisinesEmbeddedModel")
    @Data
    public static class CuisinesEmbeddedModelOpenApi {

        private List<CuisineModel> cuisines;

    }

}
