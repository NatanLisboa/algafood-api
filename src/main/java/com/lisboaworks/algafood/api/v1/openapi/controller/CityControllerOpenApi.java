package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.CityModel;
import com.lisboaworks.algafood.api.v1.model.input.CityInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cities")
public interface CityControllerOpenApi {

    @Operation(summary = "Get all the cities")
    CollectionModel<CityModel> findAll();

    @Operation(summary = "Get a city by its id")
    CityModel findById(Long cityId);

    @Operation(summary = "Register a city", description = "Register of a city. Needs a valid state and name")
    CityModel add(CityInput cityInput);

    @Operation(summary = "Update a city by its id")
    CityModel update(Long cityId, CityInput newCityInput);

    @Operation(summary = "Delete a city by its id")
    void delete(Long cityId);

}
