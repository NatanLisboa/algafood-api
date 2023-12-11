package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.ProductModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class ProductsModelOpenApi {

    private ProductsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class ProductsEmbeddedModelOpenApi {

        private List<ProductModel> products;

    }

}
