package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.assembler.PaymentMethodModelAssembler;
import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.v1.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.lisboaworks.algafood.core.security.CheckSecurity;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurants/{restaurantId}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerOpenApi {

    private final RestaurantRegisterService restaurantRegisterService;
    private final PaymentMethodModelAssembler paymentMethodModelAssembler;
    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @GetMapping
    @CheckSecurity.Restaurants.CanGet
    public CollectionModel<PaymentMethodModel> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);

        CollectionModel<PaymentMethodModel> paymentMethodsCollectionModel = paymentMethodModelAssembler
                .toCollectionModel(restaurant.getPaymentMethods())
                .removeLinks();

        paymentMethodsCollectionModel.add(algaLinks.linkToRestaurantPaymentMethods(restaurantId));

        if (securityHelper.canManageRestaurantOperation(restaurantId)) {
            paymentMethodsCollectionModel.add(algaLinks.linkToRestaurantPaymentMethodAssociation(restaurantId, "associate"));

            paymentMethodsCollectionModel.getContent().forEach(paymentMethodModel -> paymentMethodModel
                    .add(algaLinks.linkToRestaurantPaymentMethodDisassociation(restaurantId, paymentMethodModel.getId(),
                            "disassociate")
                    ));
        }

        return paymentMethodsCollectionModel;
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManageOperation
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.associatePaymentMethod(restaurantId, paymentMethodId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManageOperation
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.disassociatePaymentMethod(restaurantId, paymentMethodId);

        return ResponseEntity.noContent().build();
    }

}
