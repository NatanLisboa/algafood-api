package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.OrderController;
import com.lisboaworks.algafood.api.v1.model.OrderSummaryModel;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderSummaryModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderSummaryModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public OrderSummaryModelAssembler() {
		super(OrderController.class, OrderSummaryModel.class);
	}

	@Override
	public OrderSummaryModel toModel(Order order) {
		OrderSummaryModel orderSummaryModel = this.createModelWithId(order.getId(), order);

		modelMapper.map(order, orderSummaryModel);

		orderSummaryModel.add(algaLinks.linkToOrders("orders"));

		orderSummaryModel.getRestaurant().add(algaLinks.linkToRestaurant(order.getRestaurant().getId()));

		orderSummaryModel.getCustomer().add(algaLinks.linkToUser(order.getCustomer().getId()));

		return orderSummaryModel;
	}

}