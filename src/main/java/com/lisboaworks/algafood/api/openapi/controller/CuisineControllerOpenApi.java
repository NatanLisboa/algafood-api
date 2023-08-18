package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.api.dto.input.CuisineInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Cuisines")
public interface CuisineControllerOpenApi {

    @ApiOperation("Get all registered cuisines")
    Page<CuisineDTO> findAll(Pageable pageable);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid cuisine id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a cuisine by its id")
    CuisineDTO findById(@ApiParam(value = "Id from a cuisine", example = "1")
                            Long cuisineId);

    @ApiOperation("Register a new cuisine")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered cuisine")
    })
    CuisineDTO add(@ApiParam(name = "body", value = "New cuisine representation")
                       CuisineInput cuisineInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated cuisine"),
            @ApiResponse(responseCode = "404", description = "Cuisine not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing cuisine")
    CuisineDTO update(@ApiParam(value = "Id from a cuisine", example = "1")
                          Long cuisineId,

                          @ApiParam(name = "body", value = "Cuisine representation with new data")
                          CuisineInput newCuisineInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cuisine deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a cuisine by its id")
    void delete(@ApiParam(value = "Id from a cuisine", example = "1")
                       Long cuisineId);
}