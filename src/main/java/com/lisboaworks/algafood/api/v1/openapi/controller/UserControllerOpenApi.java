package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserModel;
import com.lisboaworks.algafood.api.v1.model.input.UserChangePasswordInput;
import com.lisboaworks.algafood.api.v1.model.input.UserInput;
import com.lisboaworks.algafood.api.v1.model.input.UserNameEmailInput;
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
@Tag(name = "Users")
public interface UserControllerOpenApi {

    @Operation(summary = "Get all the users")
    CollectionModel<UserModel> findAll();

    @Operation(summary = "Get user by id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid user id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    UserModel findById(@Parameter(description = "User id", example = "1", required = true) Long userId);

    @Operation(summary = "Register new user", responses = {
            @ApiResponse(responseCode = "201", description = "User registered successfully")
    })
    UserModel add(@RequestBody(description = "Representation of a new user", required = true) UserInput userInput);

    @Operation(summary = "Update user by id", responses = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    UserModel update(@Parameter(description = "User id", example = "1", required = true) Long userId,
                     @RequestBody(description = "Representation of the mandatory data for updating an user",
                             required = true)
                     UserNameEmailInput newUserInput);

    @Operation(summary = "Update user password", responses = {
            @ApiResponse(responseCode = "204", description = "User password updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    void changePassword(@Parameter(description = "User id", example = "1", required = true) Long userId,
                        @RequestBody(description = "Representation of the mandatory data for updating the user password",
                                required = true)
                        UserChangePasswordInput newPasswordInput);

}
