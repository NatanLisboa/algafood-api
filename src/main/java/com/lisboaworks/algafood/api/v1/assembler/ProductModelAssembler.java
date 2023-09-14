package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantProductController;
import com.lisboaworks.algafood.api.v1.model.ProductModel;
import com.lisboaworks.algafood.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public ProductModelAssembler() {
        super(RestaurantProductController.class, ProductModel.class);
    }

    public ProductModel toModel(Product product) {
        ProductModel productModel = this.createModelWithId(product.getId(), product, product.getRestaurant().getId());

        modelMapper.map(product, productModel);

        productModel.add(algaLinks.linkToRestaurantProducts(product.getRestaurant().getId(), "products"));

        productModel.add(algaLinks.linkToRestaurantProductPhoto(product.getRestaurant().getId(), product.getId(),
                "photo"));

        return productModel;
    }

}
