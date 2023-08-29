package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.OrderModel;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderModelAssembler {
	
	private final ModelMapper modelMapper;
	
	public OrderModel toModel(Order order) {
		return modelMapper.map(order, OrderModel.class);
	}
	
	public List<OrderModel> toCollectionModel(List<Order> orders) {
		return orders.stream()
				.map(this::toModel)
				.toList();		
	}

}
