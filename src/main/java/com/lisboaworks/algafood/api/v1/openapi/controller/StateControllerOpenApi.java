package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.StateModel;
import com.lisboaworks.algafood.api.v1.model.input.StateInput;
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
@Tag(name = "States")
public interface StateControllerOpenApi {

    @Operation(summary = "Get all the states")
    CollectionModel<StateModel> findAll();

    @Operation(summary = "Get a state by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid state id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "State not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    StateModel findById(@Parameter(description = "State id", example = "1", required = true) Long stateId);

    @Operation(summary = "Register a state", description = "Register of a state")
    StateModel add(@RequestBody(description = "Representation of a new state", required = true) StateInput stateInput);

    @Operation(summary = "Update a state by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid state id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "State not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    StateModel update(@Parameter(description = "State id", example = "1", required = true) Long stateId,
                      @RequestBody(description = "Representation of a state with updated data", required = true)
                      StateInput newStateInput);

    @Operation(summary = "Delete a state by its id", responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Invalid state id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "State not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    void delete(@Parameter(description = "State id", example = "1", required = true) Long stateId);

}
