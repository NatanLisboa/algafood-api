package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "User groups")
public interface UserGroupPermissionControllerOpenApi {

    @ApiOperation("Get all permissions from user group")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "User group not found")
    })
    CollectionModel<PermissionModel> findAll(@ApiParam(value = "User group id", example = "1", required = true) Long userGroupId);

    @ApiOperation("Associate permission to user group")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Permission associated successfully to user group"),
            @ApiResponse(responseCode = "404", description = "User group or permission not found")
    })
    ResponseEntity<Void> associate(@ApiParam(value = "User group id", example = "1", required = true) Long userGroupId,
                   @ApiParam(value = "Permission id", example = "2", required = true) Long permissionId);

    @ApiOperation("Disassociate permission from user group")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Permission disassociated successfully from user group"),
            @ApiResponse(responseCode = "404", description = "User group or permission not found")
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "User group id", example = "1", required = true) Long userGroupId,
                      @ApiParam(value = "Permission id", example = "2", required = true) Long permissionId);
    
}
