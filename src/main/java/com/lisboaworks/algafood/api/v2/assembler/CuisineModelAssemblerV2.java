package com.lisboaworks.algafood.api.v2.assembler;

import com.lisboaworks.algafood.api.v2.AlgaLinksV2;
import com.lisboaworks.algafood.api.v2.controller.CuisineControllerV2;
import com.lisboaworks.algafood.api.v2.model.CuisineModelV2;
import com.lisboaworks.algafood.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CuisineModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cuisine, CuisineModelV2> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinksV2 algaLinks;

	public CuisineModelAssemblerV2() {
		super(CuisineControllerV2.class, CuisineModelV2.class);
	}

	@Override
	public CuisineModelV2 toModel(Cuisine cuisine) {
		CuisineModelV2 cuisineModel = this.createModelWithId(cuisine.getId(), cuisine);

		modelMapper.map(cuisine, cuisineModel);

		cuisineModel.add(algaLinks.linkToCuisines("cuisines"));

		return cuisineModel;
	}

}
