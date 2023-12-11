package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.OrderModel;
import com.lisboaworks.algafood.api.v1.model.OrderSummaryModel;
import com.lisboaworks.algafood.api.v1.model.input.OrderInput;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface OrderControllerOpenApi {

    PagedModel<OrderSummaryModel> findAll(OrderFilter filter, Pageable pageable);

    OrderModel findById(String orderCode);

    OrderModel issue(OrderInput orderInput);

}
