package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.controller.*;
import com.lisboaworks.algafood.api.model.OrderModel;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

	@Autowired
	private ModelMapper modelMapper;

	public OrderModelAssembler() {
		super(OrderController.class, OrderModel.class);
	}

	public OrderModel toModel(Order order) {
		OrderModel orderModel = this.createModelWithId(order.getCode(), order);

		modelMapper.map(order, orderModel);

		orderModel.add(linkTo(OrderController.class).withRel("orders"));

		orderModel.getPaymentMethod().add(linkTo(methodOn(PaymentMethodController.class)
				.findById(order.getPaymentMethod().getId(), null)).withSelfRel());

		orderModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
				.findById(order.getRestaurant().getId())).withSelfRel());

		orderModel.getCustomer().add(linkTo(methodOn(UserController.class)
				.findById(order.getCustomer().getId())).withSelfRel());

		orderModel.getDeliveryAddress().getCity().add(linkTo(methodOn(CityController.class)
				.findById(order.getDeliveryAddress().getCity().getId())).withSelfRel());

		orderModel.getItems().forEach(item -> item.add(linkTo(methodOn(RestaurantProductController.class)
				.findById(order.getRestaurant().getId(), item.getProductId()))
				.withSelfRel()));

		return orderModel;
	}

}
