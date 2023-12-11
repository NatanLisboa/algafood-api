package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestaurantPaymentMethodControllerOpenApi {

    CollectionModel<PaymentMethodModel> findAll(Long restaurantId);

    ResponseEntity<Void> associate(Long restaurantId, Long paymentMethodId);

    ResponseEntity<Void> disassociate(Long restaurantId, Long paymentMethodId);

}
