package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.UserDTO;
import com.lisboaworks.algafood.api.dto.input.UserChangePasswordInput;
import com.lisboaworks.algafood.api.dto.input.UserNameEmailInput;
import com.lisboaworks.algafood.api.dto.input.UserInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Users")
public interface UserControllerOpenApi {

    @ApiOperation("Get all registered users")
    List<UserDTO> findAll();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid user id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a user by its id")
    UserDTO findById(@ApiParam(value = "Id from a user", example = "1", required = true)
                            Long userId);

    @ApiOperation("Register a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully")
    })
    UserDTO add(@ApiParam(name = "body", value = "New user representation", required = true)
                       UserInput userInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing user")
    UserDTO update(@ApiParam(value = "Id from a user", example = "1", required = true)
                          Long userId,

                          @ApiParam(name = "body", value = "User representation with new data", required = true)
                   UserNameEmailInput newUserInput);

    @ApiOperation("Change user password")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User password changed successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    void changePassword(@ApiParam(value = "Id from a user", example = "1", required = true)
                        Long userId,

                        @ApiParam(name = "body", value = "User password representation containing the current password and the new password", required = true)
                        UserChangePasswordInput newPasswordInput);

}
