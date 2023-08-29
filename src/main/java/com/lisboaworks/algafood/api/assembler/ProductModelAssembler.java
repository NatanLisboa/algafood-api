package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.ProductModel;
import com.lisboaworks.algafood.domain.model.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductModelAssembler {

    private final ModelMapper modelMapper;

    public ProductModel toModel(Product product) {
        return modelMapper.map(product, ProductModel.class);
    }

    public List<ProductModel> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(this::toModel)
                .toList();
    }

}
