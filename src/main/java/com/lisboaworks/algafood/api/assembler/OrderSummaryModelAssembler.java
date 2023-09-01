package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.OrderController;
import com.lisboaworks.algafood.api.model.OrderSummaryModel;
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

		orderSummaryModel.add(algaLinks.linkToOrders());

		orderSummaryModel.getRestaurant().add(algaLinks.linkToRestaurant(order.getRestaurant().getId()));

		orderSummaryModel.getCustomer().add(algaLinks.linkToUser(order.getCustomer().getId()));

		return orderSummaryModel;
	}

}
