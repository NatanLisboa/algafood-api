package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import com.lisboaworks.algafood.api.v1.model.input.CuisineInput;







import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cuisines")
public interface CuisineControllerOpenApi {

    @ApiOperation("Get all registered cuisines")
    PagedModel<CuisineModel> findAll(Pageable pageable);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid cuisine id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a cuisine by its id")
    CuisineModel findById(@ApiParam(value = "Id from a cuisine", example = "1", required = true)
                            Long cuisineId);

    @ApiOperation("Register a new cuisine")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered cuisine")
    })
    CuisineModel add(@ApiParam(name = "body", value = "New cuisine representation", required = true)
                       CuisineInput cuisineInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated cuisine"),
            @ApiResponse(responseCode = "404", description = "Cuisine not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing cuisine")
    CuisineModel update(@ApiParam(value = "Id from a cuisine", example = "1", required = true)
                          Long cuisineId,

                        @ApiParam(name = "body", value = "Cuisine representation with new data")
                          CuisineInput newCuisineInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cuisine deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a cuisine by its id")
    void delete(@ApiParam(value = "Id from a cuisine", example = "1", required = true)
                       Long cuisineId);
}
