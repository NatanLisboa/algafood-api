package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.UserGroupDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Users")
public interface UserUserGroupControllerOpenApi {

    @ApiOperation("Get all user groups from user")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    List<UserGroupDTO> findAll(@ApiParam(value = "User id", example = "1", required = true) Long userId);

    @ApiOperation("Associate user group to user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User group associated successfully to user"),
            @ApiResponse(responseCode = "404", description = "User or user group not found")
    })
    void associate(@ApiParam(value = "User id", example = "1", required = true) Long userId,
                   @ApiParam(value = "User group id", example = "3", required = true) Long userGroupId);

    @ApiOperation("Disassociate user group from user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User group disassociated successfully from user"),
            @ApiResponse(responseCode = "404", description = "User or user group not found")
    })
    void disassociate(@ApiParam(value = "User id", example = "1", required = true) Long userId,
                      @ApiParam(value = "User group id", example = "3", required = true) Long userGroupId);

}
