package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserGroupModel;





import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Users")
public interface UserUserGroupControllerOpenApi {

    @ApiOperation("Get all user groups from user")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    CollectionModel<UserGroupModel> findAll(@ApiParam(value = "User id", example = "1", required = true) Long userId);

    @ApiOperation("Associate user group to user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User group associated successfully to user"),
            @ApiResponse(responseCode = "404", description = "User or user group not found")
    })
    ResponseEntity<Void> associate(@ApiParam(value = "User id", example = "1", required = true) Long userId,
                   @ApiParam(value = "User group id", example = "3", required = true) Long userGroupId);

    @ApiOperation("Disassociate user group from user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User group disassociated successfully from user"),
            @ApiResponse(responseCode = "404", description = "User or user group not found")
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "User id", example = "1", required = true) Long userId,
                      @ApiParam(value = "User group id", example = "3", required = true) Long userGroupId);

}
