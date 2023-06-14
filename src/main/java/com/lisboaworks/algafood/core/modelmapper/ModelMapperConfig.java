package com.lisboaworks.algafood.core.modelmapper;

import com.lisboaworks.algafood.api.dto.AddressDTO;
import com.lisboaworks.algafood.domain.model.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Address, AddressDTO> addressToAddressDTOTypeMap = modelMapper.createTypeMap(Address.class, AddressDTO.class);
		addressToAddressDTOTypeMap.<String>addMapping(
				address -> address.getCity().getState().getName(),
				(addressDTO, stateName) -> addressDTO.getCity().setState(stateName)
		);

		return modelMapper;
	}

}
