package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class PaymentMethodsModelOpenApi {

    private PaymentMethodsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class PaymentMethodsEmbeddedModelOpenApi {

        private List<PaymentMethodModel> paymentMethods;

    }

}
