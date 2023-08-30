package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.controller.CuisineController;
import com.lisboaworks.algafood.api.model.CuisineModel;
import com.lisboaworks.algafood.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CuisineModelAssembler extends RepresentationModelAssemblerSupport<Cuisine, CuisineModel> {

	@Autowired
	private ModelMapper modelMapper;

	public CuisineModelAssembler() {
		super(CuisineController.class, CuisineModel.class);
	}

	@Override
	public CuisineModel toModel(Cuisine cuisine) {
		CuisineModel cuisineModel = this.createModelWithId(cuisine.getId(), cuisine);

		modelMapper.map(cuisine, cuisineModel);

		cuisineModel.add(linkTo(CuisineController.class).withRel("cuisines"));

		return modelMapper.map(cuisine, CuisineModel.class);
	}

}
