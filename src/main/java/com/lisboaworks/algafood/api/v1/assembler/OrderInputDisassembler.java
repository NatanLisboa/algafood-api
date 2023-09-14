package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.model.input.OrderInput;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderInputDisassembler {

	private final ModelMapper modelMapper;
	
	public Order toDomainObject(OrderInput orderInput) {
		return modelMapper.map(orderInput, Order.class);
	}

}
