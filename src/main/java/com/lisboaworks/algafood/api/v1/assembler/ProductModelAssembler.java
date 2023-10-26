package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantProductController;
import com.lisboaworks.algafood.api.v1.model.ProductModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    private final ModelMapper modelMapper;
    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @Autowired
    public ProductModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
        super(RestaurantProductController.class, ProductModel.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
        this.securityHelper = securityHelper;
    }

    public ProductModel toModel(Product product) {
        ProductModel productModel = this.createModelWithId(product.getId(), product, product.getRestaurant().getId());

        modelMapper.map(product, productModel);

        if (securityHelper.canGetRestaurants()) {
            productModel.add(algaLinks.linkToRestaurantProducts(product.getRestaurant().getId(), "products"));

            productModel.add(algaLinks.linkToRestaurantProductPhoto(product.getRestaurant().getId(), product.getId(),
                    "photo"));
        }

        return productModel;
    }

}
