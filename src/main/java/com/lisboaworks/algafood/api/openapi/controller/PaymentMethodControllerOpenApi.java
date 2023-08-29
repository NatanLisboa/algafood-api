package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.model.input.PaymentMethodInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Payment methods")
public interface PaymentMethodControllerOpenApi {

    @ApiOperation("Get all registered payment methods")
    ResponseEntity<List<PaymentMethodModel>> findAll(ServletWebRequest request);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid payment method id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Payment method not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a payment method by its id")
    ResponseEntity<PaymentMethodModel> findById(@ApiParam(value = "Id from a payment method", example = "1", required = true)
                            Long paymentMethodId, ServletWebRequest request);

    @ApiOperation("Register a new payment method")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered payment method")
    })
    PaymentMethodModel add(@ApiParam(name = "body", value = "New payment method representation", required = true)
                       PaymentMethodInput paymentMethodInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated payment method"),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing payment method")
    PaymentMethodModel update(@ApiParam(value = "Id from a payment method", example = "1", required = true)
                          Long paymentMethodId,

                              @ApiParam(name = "body", value = "Payment method representation with new data")
                          PaymentMethodInput newPaymentMethodInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment method deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment method not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a payment method by its id")
    void delete(@ApiParam(value = "Id from a payment method", example = "1", required = true)
                       Long paymentMethodId);
}
