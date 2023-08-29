package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.input.ProductInput;
import com.lisboaworks.algafood.domain.model.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductInputDisassembler {

	private final ModelMapper modelMapper;
	
	public Product toDomainObject(ProductInput productInput) {
		return modelMapper.map(productInput, Product.class);
	}
	
	public void copyToDomainObject(ProductInput productInput, Product product) {
		modelMapper.map(productInput, product);
	}
	
}
