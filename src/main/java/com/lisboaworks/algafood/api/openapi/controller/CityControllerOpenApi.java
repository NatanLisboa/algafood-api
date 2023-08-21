package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.api.dto.input.CityInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("Get all registered cities")
    List<CityDTO> findAll();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid city id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a city by its id")
    CityDTO findById(@ApiParam(value = "Id from a city", example = "1", required = true)
                            Long cityId);

    @ApiOperation("Register a new city")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered city")
    })
    CityDTO add(@ApiParam(name = "body", value = "New city representation", required = true)
                       CityInput cityInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated city"),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing city")
    CityDTO update(@ApiParam(value = "Id from a city", example = "1", required = true)
                          Long cityId,

                          @ApiParam(name = "body", value = "City representation with new data")
                          CityInput newCityInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a city by its id")
    void delete(@ApiParam(value = "Id from a city", example = "1", required = true)
                       Long cityId);
}
