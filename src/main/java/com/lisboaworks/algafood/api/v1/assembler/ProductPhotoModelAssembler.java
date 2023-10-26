package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantProductPhotoController;
import com.lisboaworks.algafood.api.v1.model.ProductPhotoModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelAssembler extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public ProductPhotoModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
		super(RestaurantProductPhotoController.class, ProductPhotoModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	public ProductPhotoModel toModel(ProductPhoto productPhoto) {
		ProductPhotoModel productPhotoModel = modelMapper.map(productPhoto, ProductPhotoModel.class);

		if (securityHelper.canGetRestaurants()) {
			productPhotoModel.add(algaLinks.linkToRestaurantProductPhoto(productPhoto.getRestaurantId(),
					productPhoto.getProduct().getId()));

			productPhotoModel.add(algaLinks.linkToRestaurantProduct(productPhoto.getRestaurantId(),
					productPhoto.getProduct().getId(), "product"));
		}

		return productPhotoModel;
	}

}
