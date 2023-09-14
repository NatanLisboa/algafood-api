package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.assembler.PaymentMethodModelAssembler;
import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.v1.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

        CollectionModel<PaymentMethodModel> paymentMethodsModel = paymentMethodModelAssembler
                .toCollectionModel(restaurant.getPaymentMethods())
                .removeLinks()
                .add(algaLinks.linkToRestaurantPaymentMethods(restaurantId))
                .add(algaLinks.linkToRestaurantPaymentMethodAssociation(restaurantId, "associate"));

        paymentMethodsModel.getContent().forEach(paymentMethodModel -> paymentMethodModel
                .add(algaLinks.linkToRestaurantPaymentMethodDisassociation(restaurantId, paymentMethodModel.getId(),
                        "disassociate")
        ));

        return paymentMethodsModel;
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.associatePaymentMethod(restaurantId, paymentMethodId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.disassociatePaymentMethod(restaurantId, paymentMethodId);

        return ResponseEntity.noContent().build();
    }

}
