package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.assembler.PaymentMethodModelAssembler;
import com.lisboaworks.algafood.api.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerOpenApi {

    private final RestaurantRegisterService restaurantRegisterService;
    private final PaymentMethodModelAssembler paymentMethodModelAssembler;
    private final AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<PaymentMethodModel> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        return paymentMethodModelAssembler.toCollectionModel(restaurant.getPaymentMethods())
                .removeLinks()
                .add(algaLinks.linkToRestaurantPaymentMethods(restaurantId));
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.associatePaymentMethod(restaurantId, paymentMethodId);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.disassociatePaymentMethod(restaurantId, paymentMethodId);
    }

}
