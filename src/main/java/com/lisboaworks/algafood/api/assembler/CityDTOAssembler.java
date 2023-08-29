package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.controller.CityController;
import com.lisboaworks.algafood.api.controller.StateController;
import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityDTOAssembler extends RepresentationModelAssemblerSupport<City, CityDTO> {

	@Autowired
	private ModelMapper modelMapper;

	public CityDTOAssembler() {
		super(CityController.class, CityDTO.class);
	}

	@Override
	public CityDTO toModel(City city) {
		CityDTO cityDTO = this.createModelWithId(city.getId(), city);

		modelMapper.map(city, cityDTO);

		cityDTO.add(linkTo(methodOn(CityController.class)
				.findAll()).withRel("cities"));

		cityDTO.getState().add(linkTo(methodOn(StateController.class)
				.findById(cityDTO.getState().getId())).withSelfRel());

		return cityDTO;
	}
	
	public CollectionModel<CityDTO> toCollectionModel(Iterable<? extends City> cities) {
		return super.toCollectionModel(cities)
				.add(linkTo(CityController.class).withSelfRel());
	}

}
