package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.RestaurantProductPhotoController;
import com.lisboaworks.algafood.api.model.ProductPhotoModel;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelAssembler extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public ProductPhotoModelAssembler() {
		super(RestaurantProductPhotoController.class, ProductPhotoModel.class);
	}

	public ProductPhotoModel toModel(ProductPhoto productPhoto) {
		ProductPhotoModel productPhotoModel = modelMapper.map(productPhoto, ProductPhotoModel.class);

		productPhotoModel.add(algaLinks.linkToRestaurantProductPhoto(productPhoto.getRestaurantId(),
				productPhoto.getProduct().getId()));

		productPhotoModel.add(algaLinks.linkToRestaurantProduct(productPhoto.getRestaurantId(),
				productPhoto.getProduct().getId(), "product"));

		return productPhotoModel;
	}

}
