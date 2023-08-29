package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.input.CuisineInput;
import com.lisboaworks.algafood.domain.model.Cuisine;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CuisineInputDisassembler {

	private final ModelMapper modelMapper;
	
	public Cuisine toDomainObject(CuisineInput cuisineInput) {
		return modelMapper.map(cuisineInput, Cuisine.class);
	}
	
	public void copyToDomainObject(CuisineInput cuisineInput, Cuisine cuisine) {
		modelMapper.map(cuisineInput, cuisine);
	}
	
}
