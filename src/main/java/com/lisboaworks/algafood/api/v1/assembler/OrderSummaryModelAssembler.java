package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.OrderController;
import com.lisboaworks.algafood.api.v1.model.OrderSummaryModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderSummaryModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderSummaryModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public OrderSummaryModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
		super(OrderController.class, OrderSummaryModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	@Override
	public OrderSummaryModel toModel(Order order) {
		OrderSummaryModel orderSummaryModel = this.createModelWithId(order.getId(), order);

		modelMapper.map(order, orderSummaryModel);

		if (securityHelper.canGetAllOrders(order.getCustomer().getId(), order.getRestaurant().getId())) {
			orderSummaryModel.add(algaLinks.linkToOrders("orders"));
		}

		if (securityHelper.canGetRestaurants()) {
			orderSummaryModel.getRestaurant().add(algaLinks.linkToRestaurant(order.getRestaurant().getId()));
		}

		if (securityHelper.canGetUsersUserGroupsAndPermissions()) {
			orderSummaryModel.getCustomer().add(algaLinks.linkToUser(order.getCustomer().getId()));
		}

		return orderSummaryModel;
	}

}
