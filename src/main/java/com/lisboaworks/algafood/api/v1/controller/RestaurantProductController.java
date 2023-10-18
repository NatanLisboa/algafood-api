package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.assembler.ProductInputDisassembler;
import com.lisboaworks.algafood.api.v1.assembler.ProductModelAssembler;
import com.lisboaworks.algafood.api.v1.model.ProductModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductInput;
import com.lisboaworks.algafood.api.v1.openapi.controller.RestaurantProductControllerOpenApi;
import com.lisboaworks.algafood.core.security.CheckSecurity;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.ProductRepository;
import com.lisboaworks.algafood.domain.service.ProductRegisterService;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurants/{restaurantId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    private final ProductRegisterService productRegisterService;
    private final RestaurantRegisterService restaurantRegisterService;
    private final ProductModelAssembler productModelAssembler;
    private final ProductInputDisassembler productInputDisassembler;
    private final ProductRepository productRepository;
    private final AlgaLinks algaLinks;

    @GetMapping
    @CheckSecurity.Restaurants.CanGet
    public CollectionModel<ProductModel> findAll(@PathVariable Long restaurantId,
                                                 @RequestParam(required = false, defaultValue = "false") Boolean includeInactiveProducts) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        List<Product> allActiveProducts;
        if (includeInactiveProducts) {
            allActiveProducts = restaurant.getProducts();
        } else {
            allActiveProducts = productRepository.findActiveProductsByRestaurant(restaurant);
        }
        return productModelAssembler.toCollectionModel(allActiveProducts)
                .add(algaLinks.linkToRestaurantProducts(restaurantId));
    }

    @GetMapping("/{productId}")
    @CheckSecurity.Restaurants.CanGet
    public ProductModel findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);
        return productModelAssembler.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Restaurants.CanManageOperation
    public ProductModel add(@PathVariable Long restaurantId, @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);
        return productModelAssembler.toModel(productRegisterService.save(product));
    }

    @PutMapping("/{productId}")
    @CheckSecurity.Restaurants.CanManageOperation
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody @Valid ProductInput updateProductInput) {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);
        productInputDisassembler.copyToDomainObject(updateProductInput, product);
        return productModelAssembler.toModel(productRegisterService.save(product));
    }

}
