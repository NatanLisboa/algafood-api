package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
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
public interface RestaurantPaymentMethodControllerOpenApi {

    @Operation(summary = "Get all the payment methods from a restaurant", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
            content = @Content(schema = @Schema(ref = "ApiException")))
    })
    CollectionModel<PaymentMethodModel> findAll(@Parameter(description = "Restaurant id", example = "1", required = true)
                                                Long restaurantId);

    @Operation(summary = "Associate payment method to a restaurant", responses = {
            @ApiResponse(responseCode = "204", description = "Payment method associated successfully to restaurant"),
            @ApiResponse(responseCode = "404", description = "Payment method or restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> associate(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                                   @Parameter(description = "Payment method id", example = "1", required = true) Long paymentMethodId);

    @Operation(summary = "Disassociate payment method from a restaurant", responses = {
            @ApiResponse(responseCode = "204", description = "Payment method disassociated successfully from restaurant"),
            @ApiResponse(responseCode = "404", description = "Payment method or restaurant not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> disassociate(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                                      @Parameter(description = "Payment method id", example = "1", required = true) Long paymentMethodId);

}
