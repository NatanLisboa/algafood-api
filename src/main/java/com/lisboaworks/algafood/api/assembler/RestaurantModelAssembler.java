package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.RestaurantModel;
import com.lisboaworks.algafood.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RestaurantModelAssembler {
	
	private final ModelMapper modelMapper;
	
	public RestaurantModel toModel(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantModel.class);
	}
	
	public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toModel(restaurant))
				.toList();		
	}

}
