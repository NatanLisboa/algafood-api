package com.lisboaworks.algafood.api.openapi;

import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.api.dto.input.OrderInput;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by a comma",
                    name = "fields", paramType = "query", type = "string")
    })
    Page<OrderSummaryDTO> findAll(OrderFilter filter, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by a comma",
                    name = "fields", paramType = "query", type = "string")
    })
    OrderDTO findById(String orderCode);

    OrderDTO issue(OrderInput orderInput);

}
