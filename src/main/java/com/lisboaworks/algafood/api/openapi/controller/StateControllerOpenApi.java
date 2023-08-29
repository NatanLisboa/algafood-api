package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.model.StateModel;
import com.lisboaworks.algafood.api.model.input.StateInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("Get all registered states")
    List<StateModel> findAll();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid state id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "State not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a state by its id")
    StateModel findById(@ApiParam(value = "Id from a state", example = "1", required = true)
                            Long stateId);

    @ApiOperation("Register a new state")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered state")
    })
    StateModel add(@ApiParam(name = "body", value = "New state representation", required = true)
                       StateInput stateInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated state"),
            @ApiResponse(responseCode = "404", description = "State not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing state")
    StateModel update(@ApiParam(value = "Id from a state", example = "1", required = true)
                          Long stateId,

                      @ApiParam(name = "body", value = "State representation with new data", required = true)
                          StateInput newStateInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "State deleted successfully"),
            @ApiResponse(responseCode = "404", description = "State not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a state by its id")
    void delete(@ApiParam(value = "Id from a state", example = "1", required = true)
                       Long stateId);
}
