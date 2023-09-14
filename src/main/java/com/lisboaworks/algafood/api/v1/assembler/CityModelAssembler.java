package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.CityController;
import com.lisboaworks.algafood.api.v1.model.CityModel;
import com.lisboaworks.algafood.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public CityModelAssembler() {
		super(CityController.class, CityModel.class);
	}

	@Override
	public CityModel toModel(City city) {
		CityModel cityModel = this.createModelWithId(city.getId(), city);

		modelMapper.map(city, cityModel);

		cityModel.add(algaLinks.linkToCities("cities"));

		cityModel.getState().add(algaLinks.linkToState(cityModel.getState().getId()));

		return cityModel;
	}

	public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> cities) {
		return super.toCollectionModel(cities)
				.add(linkTo(CityController.class).withSelfRel());
	}

}
