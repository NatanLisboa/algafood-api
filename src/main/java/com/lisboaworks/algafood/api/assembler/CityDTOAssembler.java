package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.domain.model.City;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CityDTOAssembler {
	
	private final ModelMapper modelMapper;
	
	public CityDTO toDTO(City city) {
		return modelMapper.map(city, CityDTO.class);
	}
	
	public List<CityDTO> toDTOList(List<City> cities) {
		return cities.stream()
				.map(city -> toDTO(city))
				.toList();		
	}

}
