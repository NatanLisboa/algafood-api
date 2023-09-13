package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.RestaurantSummaryModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantsSummaryModel")
@Data
public class RestaurantsSummaryModelOpenApi {

    private RestaurantsSummaryEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("RestaurantsSummaryEmbeddedModel")
    @Data
    public static class RestaurantsSummaryEmbeddedModelOpenApi {

        private List<RestaurantSummaryModel> restaurants;

    }

}
