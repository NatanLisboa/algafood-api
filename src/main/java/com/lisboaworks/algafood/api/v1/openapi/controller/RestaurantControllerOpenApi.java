package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.RestaurantModel;
import com.lisboaworks.algafood.api.v1.model.RestaurantOnlyNameModel;
import com.lisboaworks.algafood.api.v1.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.api.v1.model.input.RestaurantInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
public interface RestaurantControllerOpenApi {

    CollectionModel<RestaurantSummaryModel> findAll();

    CollectionModel<RestaurantOnlyNameModel> findAllOnlyWithName();

    RestaurantModel findById(Long restaurantId);

    RestaurantModel add(RestaurantInput restaurantInput);

    RestaurantModel update(Long restaurantId, RestaurantInput newRestaurantInput);

    ResponseEntity<Void> activate(Long restaurantId);

    ResponseEntity<Void> inactivate(Long restaurantId);

    ResponseEntity<Void> open(Long restaurantId);

    ResponseEntity<Void> close(Long restaurantId);

    void activateMultiples(List<Long> restaurantIds);

    void inactivateMultiples(List<Long> restaurantIds);

}
