package com.lisboaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.input.RestaurantInput;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;

@Component
public class RestaurantInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurant toDomainObject(RestaurantInput restaurantInput) {
		return modelMapper.map(restaurantInput, Restaurant.class);
	}
	
	public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
		restaurant.setCuisine(new Cuisine()); //To avoid org.hibernate.HibernateException: identifier of an instance of com.lisboaworks.algafood.domain.model.Cuisine was altered from 1 to 2
		modelMapper.map(restaurantInput, restaurant);
	}
	
}
