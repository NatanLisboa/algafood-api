package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.controller.*;
import com.lisboaworks.algafood.api.model.OrderModel;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
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

		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
				new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

		String ordersUrl = linkTo(OrderController.class).toUri().toString();

		orderModel.add(Link.of(UriTemplate.of(ordersUrl, pageVariables), "orders"));

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
