package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.PaymentMethodDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantPaymentMethodControllerOpenApi {

    @ApiOperation("Get all payment methods from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    List<PaymentMethodDTO> findAll(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Associate payment method to restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment method associated successfully to restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant or payment method not found")
    })
    void associate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "Payment method id", example = "3", required = true) Long paymentMethodId);

    @ApiOperation("Disassociate payment method from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment method disassociated successfully from restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant or payment method not found")
    })
    void disassociate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "Payment method id", example = "3", required = true) Long paymentMethodId);

}