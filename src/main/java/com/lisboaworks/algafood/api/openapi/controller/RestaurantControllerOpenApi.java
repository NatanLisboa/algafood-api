package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.model.RestaurantModel;
import com.lisboaworks.algafood.api.model.RestaurantOnlyNameModel;
import com.lisboaworks.algafood.api.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.api.model.input.RestaurantInput;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantControllerOpenApi {

    @ApiOperation(value = "Get restaurants")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Order projections name",
                    allowableValues = "only-name",
                    name = "projection",
                    paramType = "query",
                    type = "string",
                    dataTypeClass = String.class)
    })
    CollectionModel<RestaurantSummaryModel> findAll();

    @ApiIgnore
    @ApiOperation(value = "Get restaurants", hidden = true)
    CollectionModel<RestaurantOnlyNameModel> findAllOnlyWithName();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Get restaurant by id")
    RestaurantModel findById(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurant registered successfully")
    })
    @ApiOperation(value = "Add new restaurant")
    RestaurantModel add(
                    @ApiParam(name = "body", value = "New restaurant representation", required = true)
                    RestaurantInput restaurantInput
    );

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Update restaurant")
    RestaurantModel update(@ApiParam(value = "Restaurant id", example = "1", required = true)
                         Long restaurantId,

                           @ApiParam(name = "body", value = "Restaurant representation with new data", required = true)
                         RestaurantInput newRestaurantInput
    );

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant activated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Activate restaurant")
    ResponseEntity<Void> activate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant inactivated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Inactivate restaurant")
    ResponseEntity<Void> inactivate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant opened successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Open restaurant")
    ResponseEntity<Void> open(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant closed successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Close restaurant")
    ResponseEntity<Void> close(@ApiParam(value = "Restaurant id", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurants activated successfully")
    })
    @ApiOperation(value = "Activate multiple restaurants")
    void activateMultiples(@ApiParam(value = "Restaurants ids", required = true) List<Long> restaurantIds);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurants inactivated successfully")
    })
    @ApiOperation(value = "Inactivate multiple restaurants")
    void inactivateMultiples(@ApiParam(value = "Restaurants ids", required = true) List<Long> restaurantIds);

}
