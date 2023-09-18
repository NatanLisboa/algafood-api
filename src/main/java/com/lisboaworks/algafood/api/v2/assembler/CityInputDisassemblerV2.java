package com.lisboaworks.algafood.api.v2.assembler;

import com.lisboaworks.algafood.api.v2.model.input.CityInputV2;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CityInputDisassemblerV2 {

	private final ModelMapper modelMapper;
	
	public City toDomainObject(CityInputV2 cityInput) {
		return modelMapper.map(cityInput, City.class);
	}

	public void copyToDomainObject(CityInputV2 cityInput, City city) {
		city.setState(new State()); //To avoid org.hibernate.HibernateException: identifier of an instance of com.lisboaworks.algafood.domain.model.State was altered from 1 to 2
		modelMapper.map(cityInput, city);
	}
	
}
