package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.ProductPhotoModel;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductPhotoModelAssembler {
	
	private final ModelMapper modelMapper;
	
	public ProductPhotoModel toModel(ProductPhoto productPhoto) {
		return modelMapper.map(productPhoto, ProductPhotoModel.class);
	}

}
