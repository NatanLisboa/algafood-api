package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.OrderModel;
import com.lisboaworks.algafood.api.v1.model.OrderSummaryModel;
import com.lisboaworks.algafood.api.v1.model.input.OrderInput;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Orders")
public interface OrderControllerOpenApi {

    @Operation(summary = "Get all the orders", parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "customerId",
                    description = "Customer id for search filter",
                    example = "1", schema = @Schema(type = "integer")),
            @Parameter(in = ParameterIn.QUERY, name = "restaurantId",
                    description = "Restaurant id for search filter",
                    example = "1", schema = @Schema(type = "integer")),
            @Parameter(in = ParameterIn.QUERY, name = "startCreationDatetime",
                    description = "Initial creation datetime for search filter",
                    example = "2023-01-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
            @Parameter(in = ParameterIn.QUERY, name = "endCreationDatetime",
                    description = "Final creation datetime for search filter",
                    example = "2023-12-31T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
    })
    PagedModel<OrderSummaryModel> findAll(@Parameter(hidden = true) OrderFilter filter,
                                          @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Get an order by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid order id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    OrderModel findById(@Parameter(description = "Cuisine id", example = "1", required = true) String orderCode);

    @Operation(summary = "Issue an order", responses = {
            @ApiResponse(responseCode = "201", description = "Order issued successfully")
    })
    OrderModel issue(OrderInput orderInput);

}
