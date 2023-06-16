package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.domain.model.Cuisine;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CuisineDTOAssembler {
	
	private final ModelMapper modelMapper;
	
	public CuisineDTO toDTO(Cuisine cuisine) {
		return modelMapper.map(cuisine, CuisineDTO.class);
	}
	
	public List<CuisineDTO> toDTOList(List<Cuisine> cuisines) {
		return cuisines.stream()
				.map(cuisine -> toDTO(cuisine))
				.toList();		
	}

}
