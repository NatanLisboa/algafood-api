package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import com.lisboaworks.algafood.api.v1.model.input.UserGroupInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "User groups")
public interface UserGroupControllerOpenApi {

    @Operation(summary = "Get all the user groups")
    CollectionModel<UserGroupModel> findAll();

    @Operation(summary = "Get a user group by its id")
    UserGroupModel findById(@Parameter(description = "User group id", example = "1", required = true) Long userGroupId);

    @Operation(summary = "Register a user group")
    UserGroupModel add(@RequestBody(description = "Representation of a new user group", required = true) UserGroupInput userGroupInput);

    @Operation(summary = "Update a user group by its id")
    UserGroupModel update(@Parameter(description = "User group id", example = "1", required = true) Long userGroupId,
                          @RequestBody(description = "Representation of a user group with updated data", required = true) UserGroupInput newUserGroupInput);

    @Operation(summary = "Delete a user group by its id")
    void delete(@Parameter(description = "User group id", example = "1", required = true) Long userGroupId);

}
