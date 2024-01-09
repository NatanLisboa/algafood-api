package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import com.lisboaworks.algafood.api.v1.model.input.CuisineInput;
import com.lisboaworks.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cuisines")
public interface CuisineControllerOpenApi {

    @Operation(summary = "Get all the cuisines")
    @PageableParameter
    PagedModel<CuisineModel> findAll(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Get a cuisine by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid cuisine id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    CuisineModel findById(@Parameter(description = "Cuisine id", example = "1", required = true) Long cuisineId);

    @Operation(summary = "Register a cuisine", responses = {
            @ApiResponse(responseCode = "201", description = "Cuisine registered successfully")
    })
    CuisineModel add(@RequestBody(description = "Representation of a new cuisine", required = true) CuisineInput cuisineInput);

    @Operation(summary = "Update a cuisine by its id", responses = {
            @ApiResponse(responseCode = "200", description = "Cuisine updated successfully"),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    CuisineModel update(@Parameter(description = "Cuisine id", example = "1", required = true) Long cuisineId,
                        @RequestBody(description = "Representation of a cuisine with updated data", required = true) CuisineInput newCuisineInput);

    @Operation(summary = "Delete a cuisine by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Cuisine deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cuisine not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    void delete(@Parameter(description = "Cuisine id", example = "1", required = true) Long cuisineId);

}
