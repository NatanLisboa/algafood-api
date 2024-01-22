package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "User groups")
public interface UserGroupPermissionControllerOpenApi {

    @Operation(summary = "Get all permissions from an user group", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid user group id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "User group not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    CollectionModel<PermissionModel> findAll(@Parameter(description = "User group id", example = "1", required = true)
                                             Long userGroupId);

    @Operation(summary = "Associate permission to an user group", responses = {
            @ApiResponse(responseCode = "204", description = "Permission associated successfully to user group"),
            @ApiResponse(responseCode = "404", description = "Permission or user group not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> associate(@Parameter(description = "User group id", example = "1", required = true)
                                   Long userGroupId,
                                   @Parameter(description = "Permission id", example = "1", required = true)
                                   Long permissionId);

    @Operation(summary = "Disassociate permission from an user group", responses = {
            @ApiResponse(responseCode = "204", description = "Permission disassociated successfully from user group"),
            @ApiResponse(responseCode = "404", description = "Permission or user group not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> disassociate(@Parameter(description = "User group id", example = "1", required = true)
                                      Long userGroupId,
                                      @Parameter(description = "Permission id", example = "1", required = true)
                                      Long permissionId);

}
