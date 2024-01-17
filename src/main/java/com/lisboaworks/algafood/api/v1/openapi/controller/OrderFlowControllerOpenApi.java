package com.lisboaworks.algafood.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Orders")
public interface OrderFlowControllerOpenApi {

    @Operation(summary = "Order confirmation", responses = {
            @ApiResponse(responseCode = "204", description = "Order confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> confirm(@Parameter(description = "Order code", example = "522be832-c93b-4164-a390-e62114e6177d", required = true)
                                 String orderCode);

    @Operation(summary = "Order cancellation", responses = {
            @ApiResponse(responseCode = "204", description = "Order cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> cancel(@Parameter(description = "Order code", example = "522be832-c93b-4164-a390-e62114e6177d", required = true)
                                String orderCode);

    @Operation(summary = "Order delivery", responses = {
            @ApiResponse(responseCode = "204", description = "Order delivered successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ResponseEntity<Void> deliver(@Parameter(description = "Order code", example = "522be832-c93b-4164-a390-e62114e6177d", required = true)
                                 String orderCode);

}
