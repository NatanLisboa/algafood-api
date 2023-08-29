package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.OrderSummaryModel;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderSummaryModelAssembler {

	private final ModelMapper modelMapper;

	public OrderSummaryModel toModel(Order order) {
		return modelMapper.map(order, OrderSummaryModel.class);
	}

	public List<OrderSummaryModel> toCollectionModel(List<Order> orders) {
		return orders.stream()
				.map(this::toModel)
				.toList();		
	}

}
