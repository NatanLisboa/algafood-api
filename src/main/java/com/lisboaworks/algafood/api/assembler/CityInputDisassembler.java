package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.input.CityInput;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CityInputDisassembler {

	private final ModelMapper modelMapper;
	
	public City toDomainObject(CityInput cityInput) {
		return modelMapper.map(cityInput, City.class);
	}

	public void copyToDomainObject(CityInput cityInput, City city) {
		city.setState(new State()); //To avoid org.hibernate.HibernateException: identifier of an instance of com.lisboaworks.algafood.domain.model.State was altered from 1 to 2
		modelMapper.map(cityInput, city);
	}
	
}
