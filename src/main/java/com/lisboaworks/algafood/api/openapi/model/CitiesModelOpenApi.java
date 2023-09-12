package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.CityModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CitiesModelOpenApi {

    private CityEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CitiesEmbeddedModel")
    @Data
    public static class CityEmbeddedModelOpenApi {

        private List<CityModel> cities;

    }

}
