package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.ProductInputDisassembler;
import com.lisboaworks.algafood.api.assembler.ProductModelAssembler;
import com.lisboaworks.algafood.api.model.ProductModel;
import com.lisboaworks.algafood.api.model.input.ProductInput;
import com.lisboaworks.algafood.api.openapi.controller.RestaurantProductControllerOpenApi;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.ProductRepository;
import com.lisboaworks.algafood.domain.service.ProductRegisterService;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    private final ProductRegisterService productRegisterService;
    private final RestaurantRegisterService restaurantRegisterService;
    private final ProductModelAssembler productModelAssembler;
    private final ProductInputDisassembler productInputDisassembler;
    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> findAll(@PathVariable Long restaurantId,
                                      @RequestParam(required = false) boolean includeInactiveProducts) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        List<Product> allActiveProducts;
        if (includeInactiveProducts) {
            allActiveProducts = restaurant.getProducts();
        } else {
            allActiveProducts = productRepository.findActiveProductsByRestaurant(restaurant);
        }
        return productModelAssembler.toCollectionModel(allActiveProducts);
    }

    @GetMapping("/{productId}")
    public ProductModel findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);
        return productModelAssembler.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel add(@PathVariable Long restaurantId, @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);
        return productModelAssembler.toModel(productRegisterService.save(product));
    }

    @PutMapping("/{productId}")
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody @Valid ProductInput updateProductInput) {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);
        productInputDisassembler.copyToDomainObject(updateProductInput, product);
        return productModelAssembler.toModel(productRegisterService.save(product));
    }

}
