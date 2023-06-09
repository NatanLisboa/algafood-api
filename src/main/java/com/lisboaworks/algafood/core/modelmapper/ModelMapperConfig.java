package com.lisboaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.domain.model.Restaurant;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Restaurant.class, RestaurantDTO.class)
			.addMapping(Restaurant::getShippingFee, RestaurantDTO::setShippingPrice);
		
		return modelMapper;
	}

}
