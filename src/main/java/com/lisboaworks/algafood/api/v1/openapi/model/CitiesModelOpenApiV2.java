package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v2.model.CityModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CitiesModelOpenApiV2 {

    private CitiesEmbeddedModelOpenApiV2 _embedded;
    private Links _links;

    @ApiModel("CitiesEmbeddedModel")
    @Data
    public static class CitiesEmbeddedModelOpenApiV2 {

        private List<CityModelV2> cities;

    }

}
