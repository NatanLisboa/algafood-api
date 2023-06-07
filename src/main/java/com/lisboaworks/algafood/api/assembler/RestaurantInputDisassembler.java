package com.lisboaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.input.RestaurantInput;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;

@Component
public class RestaurantInputDisassembler {

	public Restaurant toDomainObject(RestaurantInput restaurantInput) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantInput.getName());
		restaurant.setShippingFee(restaurantInput.getShippingFee());
		
		Cuisine cuisine = new Cuisine();
		cuisine.setId(restaurantInput.getCuisine().getId());
		
		restaurant.setCuisine(cuisine);
		
		return restaurant;
	}
	
}
