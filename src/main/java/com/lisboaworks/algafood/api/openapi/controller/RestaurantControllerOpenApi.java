package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.api.dto.input.RestaurantInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    List<RestaurantDTO> findAll();

    @ApiOperation(value = "Get restaurants", hidden = true)
    List<RestaurantDTO> findAllOnlyWithName();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Get restaurant by id")
    RestaurantDTO findById(@ApiParam(name = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurant registered successfully")
    })
    @ApiOperation(value = "Add new restaurant")
    RestaurantDTO add(
                    @ApiParam(name = "body", value = "New restaurant representation", required = true)
                    RestaurantInput restaurantInput
    );

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Update restaurant")
    RestaurantDTO update(@ApiParam(name = "Restaurant id", example = "1", required = true)
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
    void activate(@ApiParam(name = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant inactivated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Inactivate restaurant")
    void inactivate(@ApiParam(name = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant opened successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Open restaurant")
    void open(@ApiParam(name = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurant closed successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation(value = "Close restaurant")
    void close(@ApiParam(name = "Restaurant id", required = true) Long restaurantId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurants activated successfully")
    })
    @ApiOperation(value = "Activate multiple restaurants")
    void activateMultiples(@ApiParam(name = "Restaurants ids", required = true) List<Long> restaurantIds);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurants inactivated successfully")
    })
    @ApiOperation(value = "Inactivate multiple restaurants")
    void inactivateMultiples(@ApiParam(name = "Restaurants ids", required = true) List<Long> restaurantIds);

}
