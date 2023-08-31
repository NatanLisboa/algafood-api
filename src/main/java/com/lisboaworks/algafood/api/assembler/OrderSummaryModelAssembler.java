package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.controller.OrderController;
import com.lisboaworks.algafood.api.controller.RestaurantController;
import com.lisboaworks.algafood.api.controller.UserController;
import com.lisboaworks.algafood.api.model.OrderSummaryModel;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderSummaryModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderSummaryModel> {

	@Autowired
	private ModelMapper modelMapper;

	public OrderSummaryModelAssembler() {
		super(OrderController.class, OrderSummaryModel.class);
	}

	@Override
	public OrderSummaryModel toModel(Order order) {
		OrderSummaryModel orderSummaryModel = this.createModelWithId(order.getId(), order);

		modelMapper.map(order, orderSummaryModel);

		orderSummaryModel.add(linkTo(OrderController.class).withRel("orders"));

		orderSummaryModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
				.findById(order.getRestaurant().getId())).withSelfRel());

		orderSummaryModel.getCustomer().add(linkTo(methodOn(UserController.class)
				.findById(order.getCustomer().getId())).withSelfRel());

		return orderSummaryModel;
	}

}
