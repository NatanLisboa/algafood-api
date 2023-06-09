package com.lisboaworks.algafood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.domain.model.City;

@Component
public class CityDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CityDTO toDTO(City city) {
		return modelMapper.map(city, CityDTO.class);
	}
	
	public List<CityDTO> toDTOList(List<City> cities) {
		return cities.stream()
				.map(city -> toDTO(city))
				.toList();		
	}

}
