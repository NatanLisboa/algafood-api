package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.model.input.RestaurantInput;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class RestaurantInputDisassembler {

	private final ModelMapper modelMapper;
	
	public Restaurant toDomainObject(RestaurantInput restaurantInput) {
		return modelMapper.map(restaurantInput, Restaurant.class);
	}
	
	public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
		//To avoid org.hibernate.HibernateException: identifier of an instance of com.lisboaworks.algafood.domain.model.Cuisine was altered from 1 to 2
		restaurant.setCuisine(new Cuisine());
		if (Objects.nonNull(restaurant.getAddress())) {
			restaurant.getAddress().setCity(new City());
		}

		modelMapper.map(restaurantInput, restaurant);
	}
	
}
