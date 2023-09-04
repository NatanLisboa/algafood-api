package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.OrderController;
import com.lisboaworks.algafood.api.model.OrderModel;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public OrderModelAssembler() {
		super(OrderController.class, OrderModel.class);
	}

	public OrderModel toModel(Order order) {
		OrderModel orderModel = this.createModelWithId(order.getCode(), order);

		modelMapper.map(order, orderModel);

		orderModel.add(algaLinks.linkToOrders());

		if (order.canBeConfirmed()) {
			orderModel.add(algaLinks.linkToOrderConfirmation(order.getCode(), "confirm"));
		}

		if (order.canBeCancelled()) {
			orderModel.add(algaLinks.linkToOrderCancellation(order.getCode(), "cancel"));
		}

		if (order.canBeDelivered()) {
			orderModel.add(algaLinks.linkToOrderDelivery(order.getCode(), "deliver"));
		}

		orderModel.getPaymentMethod().add(algaLinks.linkToPaymentMethod(order.getPaymentMethod().getId()));

		orderModel.getRestaurant().add(algaLinks.linkToRestaurant(order.getRestaurant().getId()));

		orderModel.getCustomer().add(algaLinks.linkToUser(order.getCustomer().getId()));

		orderModel.getDeliveryAddress().getCity().add(algaLinks.linkToCity(order.getDeliveryAddress().getCity().getId()));

		orderModel.getItems().forEach(item -> item.add(algaLinks.linkToProduct(order.getRestaurant().getId(), item.getProductId(), "product")));

		return orderModel;
	}

}
