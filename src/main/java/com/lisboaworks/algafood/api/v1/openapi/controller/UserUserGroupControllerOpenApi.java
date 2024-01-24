package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Users")
public interface UserUserGroupControllerOpenApi {

    @Operation(summary = "Get all user groups from user", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    CollectionModel<UserGroupModel> findAll(@Parameter(description = "User id", example = "2", required = true)
                                            Long userId);

    @Operation(summary = "Associate user group to user", responses = {
            @ApiResponse(responseCode = "204", description = "User group associated successfully to user"),
            @ApiResponse(responseCode = "404", description = "User or user group not found")
    })
    ResponseEntity<Void> associate(@Parameter(description = "User id", example = "2", required = true)
                                   Long userId,
                                   @Parameter(description = "User group id", example = "1", required = true)
                                   Long userGroupId);

    @Operation(summary = "Disassociate user group from user", responses = {
            @ApiResponse(responseCode = "204", description = "User group disassociated successfully from user"),
            @ApiResponse(responseCode = "404", description = "User or user group not found")
    })
    ResponseEntity<Void> disassociate(@Parameter(description = "User id", example = "2", required = true)
                                      Long userId,
                                      @Parameter(description = "User group id", example = "1", required = true)
                                      Long userGroupId);

}
