package com.lisboaworks.algafood.api.openapi.dto;

import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import io.swagger.annotations.ApiModel;


@ApiModel("OrdersDTO")
public class OrdersDTOOpenApi extends PaginatedDTOOpenAPI<OrderSummaryDTO> {

}
