package com.lisboaworks.algafood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.domain.model.Restaurant;

@Component
public class RestaurantDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestaurantDTO toDTO(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDTO.class);
	}
	
	public List<RestaurantDTO> toDTOList(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toDTO(restaurant))
				.toList();		
	}

}
