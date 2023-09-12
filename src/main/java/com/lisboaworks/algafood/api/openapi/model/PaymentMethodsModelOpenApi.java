package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.PaymentMethodModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PaymentMethodsModel")
@Data
public class PaymentMethodsModelOpenApi {

    private PaymentMethodEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PaymentMethodsEmbeddedModel")
    @Data
    public static class PaymentMethodEmbeddedModelOpenApi {

        private List<PaymentMethodModel> paymentMethods;

    }

}
