package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.CityModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class CitiesModelOpenApi {

    private CitiesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class CitiesEmbeddedModelOpenApi {

        private List<CityModel> cities;

    }

}
