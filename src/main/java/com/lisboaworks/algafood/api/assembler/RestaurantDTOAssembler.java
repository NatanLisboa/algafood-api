package com.lisboaworks.algafood.api.assembler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.domain.model.Restaurant;

@Component
public class RestaurantDTOAssembler {
	
	public RestaurantDTO toDTO(Restaurant restaurant) {
		CuisineDTO cuisine = new CuisineDTO();
    	cuisine.setId(restaurant.getCuisine().getId());
    	cuisine.setName(restaurant.getCuisine().getName());
    	
    	RestaurantDTO restaurantDTO = new RestaurantDTO();
    	restaurantDTO.setId(restaurant.getId());
    	restaurantDTO.setName(restaurant.getName());
    	restaurantDTO.setShippingFee(restaurant.getShippingFee());
    	restaurantDTO.setCuisine(cuisine);
		return restaurantDTO;
	}
	
	public List<RestaurantDTO> toDTOList(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toDTO(restaurant))
				.toList();		
	}

}
