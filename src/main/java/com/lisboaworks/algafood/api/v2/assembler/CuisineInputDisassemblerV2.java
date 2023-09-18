package com.lisboaworks.algafood.api.v2.assembler;

import com.lisboaworks.algafood.api.v2.model.input.CuisineInputV2;
import com.lisboaworks.algafood.domain.model.Cuisine;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CuisineInputDisassemblerV2 {

	private final ModelMapper modelMapper;
	
	public Cuisine toDomainObject(CuisineInputV2 cuisineInput) {
		return modelMapper.map(cuisineInput, Cuisine.class);
	}
	
	public void copyToDomainObject(CuisineInputV2 cuisineInput, Cuisine cuisine) {
		modelMapper.map(cuisineInput, cuisine);
	}
	
}
