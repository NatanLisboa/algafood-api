package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.RestaurantSummaryModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class RestaurantsSummaryModelOpenApi {

    private RestaurantsSummaryEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class RestaurantsSummaryEmbeddedModelOpenApi {

        private List<RestaurantSummaryModel> restaurants;

    }

}
