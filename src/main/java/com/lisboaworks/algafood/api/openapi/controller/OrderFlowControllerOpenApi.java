package com.lisboaworks.algafood.api.openapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Orders")
public interface OrderFlowControllerOpenApi {

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Order confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    void confirm(@ApiParam(value = "Order code", example = "522be832-c93b-4164-a390-e62114e6177d", required = true) String orderCode);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Order cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    void cancel(@ApiParam(value = "Order code", example = "522be832-c93b-4164-a390-e62114e6177d", required = true) String orderCode);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Order delivered successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    void deliver(@ApiParam(value = "Order code", example = "522be832-c93b-4164-a390-e62114e6177d", required = true) String orderCode);

}