package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserModel;





import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurants")
public interface RestaurantResponsibleUserControllerOpenApi {

    @ApiOperation("Get all responsible users from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    CollectionModel<UserModel> getAllResponsibleUsers(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Associate responsible user to restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Responsible user associated successfully to restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant or responsible user not found")
    })
    ResponseEntity<Void> associateResponsibleUser(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "Responsible user id", example = "3", required = true) Long userId);

    @ApiOperation("Disassociate responsible user from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Responsible user disassociated successfully from restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant or responsible user not found")
    })
    ResponseEntity<Void> disassociateResponsibleUser(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "Responsible user id", example = "3", required = true) Long userId);

}
