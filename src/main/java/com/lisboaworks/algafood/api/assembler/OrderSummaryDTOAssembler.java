package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderSummaryDTOAssembler {

	private final ModelMapper modelMapper;

	public OrderSummaryDTO toDTO(Order order) {
		return modelMapper.map(order, OrderSummaryDTO.class);
	}

	public List<OrderSummaryDTO> toDTOList(List<Order> orders) {
		return orders.stream()
				.map(this::toDTO)
				.toList();		
	}

}
