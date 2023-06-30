package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderDTOAssembler {
	
	private final ModelMapper modelMapper;
	
	public OrderDTO toDTO(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public List<OrderDTO> toDTOList(List<Order> orders) {
		return orders.stream()
				.map(this::toDTO)
				.toList();		
	}

}
