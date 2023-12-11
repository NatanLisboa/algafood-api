package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface RestaurantPaymentMethodControllerOpenApi {

    CollectionModel<PaymentMethodModel> findAll(Long restaurantId);

    ResponseEntity<Void> associate(Long restaurantId, Long paymentMethodId);

    ResponseEntity<Void> disassociate(Long restaurantId, Long paymentMethodId);

}
