package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.api.dto.input.OrderInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Orders")
public interface OrderControllerOpenApi {

    @ApiOperation("Get registered orders page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by a comma",
                    name = "fields", paramType = "query", type = "string")
    })
    Page<OrderSummaryDTO> findAll(OrderFilter filter, Pageable pageable);

    @ApiOperation("Get a registered order by its code")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by a comma",
                    name = "fields", paramType = "query", type = "string")
    })
    OrderDTO findById(String orderCode);

    @ApiOperation("Issue a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order issued successfully")
    })
    OrderDTO issue(OrderInput orderInput);

}
