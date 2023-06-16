package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RestaurantDTOAssembler {
	
	private final ModelMapper modelMapper;
	
	public RestaurantDTO toDTO(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDTO.class);
	}
	
	public List<RestaurantDTO> toDTOList(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toDTO(restaurant))
				.toList();		
	}

}
