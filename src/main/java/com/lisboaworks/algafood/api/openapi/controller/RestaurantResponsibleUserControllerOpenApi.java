package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantResponsibleUserControllerOpenApi {

    @ApiOperation("Get all responsible users from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    List<UserDTO> getAllResponsibleUsers(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Associate responsible user to restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Responsible user associated successfully to restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant or responsible user not found")
    })
    void associateResponsibleUser(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "Responsible user id", example = "3", required = true) Long userId);

    @ApiOperation("Disassociate responsible user from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Responsible user disassociated successfully from restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant or responsible user not found")
    })
    void disassociateResponsibleUser(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "Responsible user id", example = "3", required = true) Long userId);

}