package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.OrderController;
import com.lisboaworks.algafood.api.v1.model.OrderModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public OrderModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks,
							   SecurityHelper securityHelper) {
		super(OrderController.class, OrderModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	public OrderModel toModel(Order order) {
		OrderModel orderModel = this.createModelWithId(order.getCode(), order);

		modelMapper.map(order, orderModel);

		if (securityHelper.canGetAllOrders()) {
			orderModel.add(algaLinks.linkToOrders("orders"));
		}

		if (securityHelper.canManageOrder(order.getCode())) {
			if (order.canBeConfirmed()) {
				orderModel.add(algaLinks.linkToOrderConfirmation(order.getCode(), "confirm"));
			}

			if (order.canBeCancelled()) {
				orderModel.add(algaLinks.linkToOrderCancellation(order.getCode(), "cancel"));
			}

			if (order.canBeDelivered()) {
				orderModel.add(algaLinks.linkToOrderDelivery(order.getCode(), "deliver"));
			}
		}

		if (securityHelper.canGetPaymentMethods()) {
			orderModel.getPaymentMethod().add(algaLinks.linkToPaymentMethod(order.getPaymentMethod().getId()));
		}

		if (securityHelper.canGetRestaurants()) {
			orderModel.getRestaurant().add(algaLinks.linkToRestaurant(order.getRestaurant().getId()));
			orderModel.getItems()
					.forEach(item -> item.add(algaLinks.linkToRestaurantProduct(order.getRestaurant().getId(),
							item.getProductId(), "product")));
		}

		if (securityHelper.canGetUsersUserGroupsAndPermissions()) {
			orderModel.getCustomer().add(algaLinks.linkToUser(order.getCustomer().getId()));
		}

		if (securityHelper.canGetCities()) {
			orderModel.getDeliveryAddress().getCity().add(algaLinks.linkToCity(order.getDeliveryAddress().getCity().getId()));
		}

		return orderModel;
	}

}
