package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.ProductDTOAssembler;
import com.lisboaworks.algafood.api.assembler.ProductInputDisassembler;
import com.lisboaworks.algafood.api.dto.ProductDTO;
import com.lisboaworks.algafood.api.dto.input.ProductInput;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.service.ProductRegisterService;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
@AllArgsConstructor
public class RestaurantProductController {

    private final ProductRegisterService productRegisterService;
    private final RestaurantRegisterService restaurantRegisterService;
    private final ProductDTOAssembler productDTOAssembler;
    private final ProductInputDisassembler productInputDisassembler;

    @GetMapping
    public List<ProductDTO> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        return productDTOAssembler.toDTOList(restaurant.getProducts());
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);
        return productDTOAssembler.toDTO(product);
    }

    @PostMapping
    public ProductDTO add(@PathVariable Long restaurantId, @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);
        return productDTOAssembler.toDTO(productRegisterService.save(product));
    }
    @PutMapping("/{productId}")
    public ProductDTO update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody @Valid ProductInput updateProductInput) {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);
        productInputDisassembler.copyToDomainObject(updateProductInput, product);
        return productDTOAssembler.toDTO(productRegisterService.save(product));
    }

}
