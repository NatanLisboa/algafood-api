package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.UserGroupDTO;
import com.lisboaworks.algafood.api.dto.input.UserGroupInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "User groups")
public interface UserGroupControllerOpenApi {

    @ApiOperation("Get all registered user groups")
    List<UserGroupDTO> findAll();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid user group id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "User group not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a user group by its id")
    UserGroupDTO findById(@ApiParam(value = "Id from a user group", example = "1", required = true)
                            Long userGroupId);

    @ApiOperation("Register a new user group")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered user group")
    })
    UserGroupDTO add(@ApiParam(name = "body", value = "New user group representation", required = true)
                       UserGroupInput userGroupInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated user group"),
            @ApiResponse(responseCode = "404", description = "User group not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing user group")
    UserGroupDTO update(@ApiParam(value = "Id from a user group", example = "1", required = true)
                          Long userGroupId,

                          @ApiParam(name = "body", value = "User group representation with new data", required = true)
                          UserGroupInput newUserGroupInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User group deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User group not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a user group by its id")
    void delete(@ApiParam(value = "Id from a user group", example = "1", required = true)
                       Long userGroupId);
}
