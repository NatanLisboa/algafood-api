package com.lisboaworks.algafood.api.v2.openapi;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.v2.model.CityModelV2;
import com.lisboaworks.algafood.api.v2.model.input.CityInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cities")
public interface CityControllerOpenApiV2 {

    @ApiOperation("Get all registered cities")
    CollectionModel<CityModelV2> findAll();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid city id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a city by its id")
    CityModelV2 findById(@ApiParam(value = "Id from a city", example = "1", required = true)
                            Long cityId);

    @ApiOperation("Register a new city")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered city")
    })
    CityModelV2 add(@ApiParam(name = "body", value = "New city representation", required = true)
                    CityInputV2 cityInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated city"),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing city")
    CityModelV2 update(@ApiParam(value = "Id from a city", example = "1", required = true)
                          Long cityId,

                     @ApiParam(name = "body", value = "City representation with new data")
                          CityInputV2 newCityInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a city by its id")
    void delete(@ApiParam(value = "Id from a city", example = "1", required = true)
                       Long cityId);
}
