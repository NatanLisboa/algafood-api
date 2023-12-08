package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "paymentMethods")
@Getter
@Setter
public class PaymentMethodModel extends RepresentationModel<PaymentMethodModel> {


    private Long id;


    private String description;

}
