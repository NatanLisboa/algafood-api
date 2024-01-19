package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserModel;
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
@Tag(name = "Restaurants")
public interface RestaurantResponsibleUserControllerOpenApi {

    @Operation(summary = "Get all the responsible users from a restaurant", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException")))
    })
    CollectionModel<UserModel> getAllResponsibleUsers(@Parameter(description = "Restaurant id", example = "1", required = true) 
                                                      Long restaurantId);

    @Operation(summary = "Associate responsible user to a restaurant", responses = {
            @ApiResponse(responseCode = "204", description = "User associated successfully to restaurant"),
            @ApiResponse(responseCode = "404", description = "User or restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> associateResponsibleUser(@Parameter(description = "Restaurant id", example = "1", required = true)
                                                  Long restaurantId,
                                                  @Parameter(description = "User id", example = "1", required = true) 
                                                  Long userId);

    @Operation(summary = "Disassociate responsible user from a restaurant", responses = {
            @ApiResponse(responseCode = "204", description = "User disassociated successfully from restaurant"),
            @ApiResponse(responseCode = "404", description = "User or restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> disassociateResponsibleUser(@Parameter(description = "Restaurant id", example = "1", required = true) 
                                                     Long restaurantId,
                                                     @Parameter(description = "User id", example = "1", required = true)
                                                     Long userId);

}
