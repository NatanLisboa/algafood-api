package com.lisboaworks.algafood.core.modelmapper;

import com.lisboaworks.algafood.api.v1.model.AddressModel;
import com.lisboaworks.algafood.api.v1.model.input.OrderItemInput;
import com.lisboaworks.algafood.api.v2.model.input.CityInputV2;
import com.lisboaworks.algafood.domain.model.Address;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
				.addMappings(mapper -> mapper.skip(OrderItem::setId));
		TypeMap<Address, AddressModel> addressToAddressModelTypeMap = modelMapper.createTypeMap(Address.class, AddressModel.class);
		addressToAddressModelTypeMap.<String>addMapping(
				address -> address.getCity().getState().getName(),
				(addressModel, stateName) -> addressModel.getCity().setState(stateName)
		);

		modelMapper.createTypeMap(CityInputV2.class, City.class)
				.addMappings(mapper -> mapper.skip(City::setId));

		return modelMapper;
	}

}
