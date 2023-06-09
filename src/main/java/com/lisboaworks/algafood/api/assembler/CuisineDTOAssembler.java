package com.lisboaworks.algafood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.domain.model.Cuisine;

@Component
public class CuisineDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CuisineDTO toDTO(Cuisine cuisine) {
		return modelMapper.map(cuisine, CuisineDTO.class);
	}
	
	public List<CuisineDTO> toDTOList(List<Cuisine> cuisines) {
		return cuisines.stream()
				.map(cuisine -> toDTO(cuisine))
				.toList();		
	}

}
