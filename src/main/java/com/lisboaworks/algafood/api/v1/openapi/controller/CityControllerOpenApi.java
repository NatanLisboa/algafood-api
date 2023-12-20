package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.CityModel;
import com.lisboaworks.algafood.api.v1.model.input.CityInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cities")
public interface CityControllerOpenApi {

    @Operation(summary = "Get all the cities")
    CollectionModel<CityModel> findAll();

    @Operation(summary = "Get a city by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid city id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    CityModel findById(@Parameter(description = "City id", example = "1", required = true) Long cityId);

    @Operation(summary = "Register a city", description = "Register of a city. Needs a valid state and name")
    CityModel add(@RequestBody(description = "Representation of a new city", required = true) CityInput cityInput);

    @Operation(summary = "Update a city by its id")
    CityModel update(@Parameter(description = "City id", example = "1", required = true) Long cityId,
                     @RequestBody(description = "Representation of a city with updated data", required = true) CityInput newCityInput);

    @Operation(summary = "Delete a city by its id")
    void delete(@Parameter(description = "City id", example = "1", required = true) Long cityId);

}
