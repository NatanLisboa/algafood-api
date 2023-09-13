package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.ProductModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ProductsModel")
@Data
public class ProductsModelOpenApi {

    private ProductsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("ProductsEmbeddedModel")
    @Data
    public static class ProductsEmbeddedModelOpenApi {

        private List<ProductModel> products;

    }

}
