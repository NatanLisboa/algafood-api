package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.CuisineModel;
import com.lisboaworks.algafood.domain.model.Cuisine;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CuisineModelAssembler {
	
	private final ModelMapper modelMapper;
	
	public CuisineModel toModel(Cuisine cuisine) {
		return modelMapper.map(cuisine, CuisineModel.class);
	}
	
	public List<CuisineModel> toCollectionModel(List<Cuisine> cuisines) {
		return cuisines.stream()
				.map(cuisine -> toModel(cuisine))
				.toList();		
	}

}
