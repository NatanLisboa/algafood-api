package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.ProductPhotoDTO;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductPhotoDTOAssembler {
	
	private final ModelMapper modelMapper;
	
	public ProductPhotoDTO toDTO(ProductPhoto productPhoto) {
		return modelMapper.map(productPhoto, ProductPhotoDTO.class);
	}

}
