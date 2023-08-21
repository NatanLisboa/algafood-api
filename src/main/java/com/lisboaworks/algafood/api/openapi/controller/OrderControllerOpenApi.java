package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.api.dto.input.OrderInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import io.swagger.annotations.*;
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
                    name = "fields", paramType = "query", type = "string", dataTypeClass = String.class)
    })
    Page<OrderSummaryDTO> findAll(OrderFilter filter, Pageable pageable);

    @ApiOperation("Get a registered order by its code")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by a comma",
                    name = "fields", paramType = "query", type = "string", dataTypeClass = String.class)
    })
    OrderDTO findById(@ApiParam(example = "47c3f41a-481e-4c9c-b55c-802be76edf34", required = true) String orderCode);

    @ApiOperation("Issue a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order issued successfully")
    })
    OrderDTO issue(@ApiParam(name = "body", required = true) OrderInput orderInput);

}
