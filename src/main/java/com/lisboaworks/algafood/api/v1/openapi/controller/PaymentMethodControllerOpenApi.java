package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.v1.model.input.PaymentMethodInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Payment methods")
public interface PaymentMethodControllerOpenApi {

    @Operation(summary = "Get all the payment methods")
    ResponseEntity<CollectionModel<PaymentMethodModel>> findAll(ServletWebRequest request);

    @Operation(summary = "Get a payment method by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid payment method id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "Payment method not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<PaymentMethodModel> findById(@Parameter(description = "Payment method id", example = "1", required = true) Long paymentMethodId,
                                                ServletWebRequest request);

    @Operation(summary = "Register a payment method", responses = {
            @ApiResponse(responseCode = "201", description = "Payment method registered successfully")
    })
    PaymentMethodModel add(@RequestBody(description = "Representation of a new payment method", required = true) PaymentMethodInput paymentMethodInput);

    @Operation(summary = "Update a payment method by its id", responses = {
            @ApiResponse(responseCode = "200", description = "Payment method updated successfully"),
            @ApiResponse(responseCode = "404", description = "Payment method not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    PaymentMethodModel update(@Parameter(description = "Payment method id", example = "1", required = true) Long paymentMethodId,
                              @RequestBody(description = "Representation of a payment method with updated data", required = true) PaymentMethodInput newPaymentMethodInput);

    @Operation(summary = "Delete a payment method by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Payment method deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment method not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    void delete(@Parameter(description = "Payment method id", example = "1", required = true) Long paymentMethodId);

}
