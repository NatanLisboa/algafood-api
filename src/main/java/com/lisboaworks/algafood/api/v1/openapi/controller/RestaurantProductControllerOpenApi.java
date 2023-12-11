package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.ProductModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductInput;
import org.springframework.hateoas.CollectionModel;

public interface RestaurantProductControllerOpenApi {

    CollectionModel<ProductModel> findAll(Long restaurantId, Boolean includeInactiveProducts);

    ProductModel findById(Long restaurantId, Long productId);

    ProductModel add(Long restaurantId, ProductInput productInput);

    ProductModel update(Long restaurantId, Long productId, ProductInput updateProductInput);

}
