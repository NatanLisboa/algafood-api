package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.RestaurantModel;
import com.lisboaworks.algafood.api.v1.model.RestaurantOnlyNameModel;
import com.lisboaworks.algafood.api.v1.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.api.v1.model.input.RestaurantInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurants")
public interface RestaurantControllerOpenApi {

    @Operation(summary = "Get all the restaurants",
        parameters = {
            @Parameter(name = "projection",
            description = "Projection name",
            example = "only-name",
            in = ParameterIn.QUERY)
    })
    CollectionModel<RestaurantSummaryModel> findAll();

    @Operation(hidden = true)
    CollectionModel<RestaurantOnlyNameModel> findAllOnlyWithName();

    @Operation(summary = "Get a restaurant by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    RestaurantModel findById(Long restaurantId);

    @Operation(summary = "Register a restaurant", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurant registered successfully")
    })
    RestaurantModel add(@RequestBody(description = "Representation of a new restaurant", required = true) RestaurantInput restaurantInput);

    @Operation(summary = "Update a restaurant by its id", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    RestaurantModel update(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                           @RequestBody(description = "Representation of a restaurant with updated data", required = true)
                           RestaurantInput newRestaurantInput);

    @Operation(summary = "Activate a restaurant by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant activated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> activate(@Parameter(description = "Restaurant id", example = "1", required = true)
                                  Long restaurantId);

    @Operation(summary = "Inactivate a restaurant by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant inactivated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> inactivate(@Parameter(description = "Restaurant id", example = "1", required = true)
                                    Long restaurantId);

    @Operation(summary = "Open a restaurant by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant opened successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> open(@Parameter(description = "Restaurant id", example = "1", required = true)
                              Long restaurantId);

    @Operation(summary = "Close a restaurant by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant closed successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> close(@Parameter(description = "Restaurant id", example = "1", required = true)
                               Long restaurantId);

    @Operation(summary = "Activate multiple restaurants by their ids", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurants activated successfully"),
    })
    void activateMultiples(@RequestBody(description = "Restaurants ids", required = true) List<Long> restaurantIds);

    @Operation(summary = "Inactivate multiple restaurants by their ids", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurants inactivated successfully"),
    })
    void inactivateMultiples(@RequestBody(description = "Restaurants ids", required = true) List<Long> restaurantIds);

}
