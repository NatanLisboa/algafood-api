package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.CuisineController;
import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CuisineModelAssembler extends RepresentationModelAssemblerSupport<Cuisine, CuisineModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public CuisineModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
		super(CuisineController.class, CuisineModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	@Override
	public CuisineModel toModel(Cuisine cuisine) {
		CuisineModel cuisineModel = this.createModelWithId(cuisine.getId(), cuisine);

		modelMapper.map(cuisine, cuisineModel);

		if (securityHelper.canGetCuisines()) {
			cuisineModel.add(algaLinks.linkToCuisines("cuisines"));
		}

		return cuisineModel;
	}

}
