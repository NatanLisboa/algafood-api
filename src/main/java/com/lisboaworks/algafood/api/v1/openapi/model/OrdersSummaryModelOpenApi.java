package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.OrderSummaryModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@Getter
@Setter
public class OrdersSummaryModelOpenApi {

    private OrdersSummaryEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @Data
    public static class OrdersSummaryEmbeddedModelOpenApi {

        private List<OrderSummaryModel> orders;

    }

}
