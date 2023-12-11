package com.lisboaworks.algafood.api.v1.model;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderSummaryModel extends RepresentationModel<OrderSummaryModel> {


    private String code;


    private BigDecimal subtotal;


    private BigDecimal shippingFee;


    private BigDecimal totalValue;


    private OffsetDateTime creationDatetime;

    private RestaurantOnlyNameModel restaurant;
    private UserModel customer;
    private OrderStatus status;

}
