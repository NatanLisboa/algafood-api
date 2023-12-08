package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import com.lisboaworks.algafood.api.v1.model.input.UserGroupInput;







import org.springframework.hateoas.CollectionModel;

@Api(tags = "User groups")
public interface UserGroupControllerOpenApi {

    @ApiOperation("Get all registered user groups")
    CollectionModel<UserGroupModel> findAll();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid user group id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "User group not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a user group by its id")
    UserGroupModel findById(@ApiParam(value = "Id from a user group", example = "1", required = true)
                            Long userGroupId);

    @ApiOperation("Register a new user group")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered user group")
    })
    UserGroupModel add(@ApiParam(name = "body", value = "New user group representation", required = true)
                       UserGroupInput userGroupInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated user group"),
            @ApiResponse(responseCode = "404", description = "User group not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing user group")
    UserGroupModel update(@ApiParam(value = "Id from a user group", example = "1", required = true)
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
