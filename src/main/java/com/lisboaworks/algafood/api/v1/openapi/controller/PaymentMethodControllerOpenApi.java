package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.v1.model.input.PaymentMethodInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

public interface PaymentMethodControllerOpenApi {

    ResponseEntity<CollectionModel<PaymentMethodModel>> findAll(ServletWebRequest request);

    ResponseEntity<PaymentMethodModel> findById(Long paymentMethodId, ServletWebRequest request);

    PaymentMethodModel add(PaymentMethodInput paymentMethodInput);

    PaymentMethodModel update(Long paymentMethodId, PaymentMethodInput newPaymentMethodInput);

    void delete(Long paymentMethodId);

}
