package com.lisboaworks.algafood.api.v1.model;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderModel extends RepresentationModel<OrderModel> {


    private String code;


    private BigDecimal subtotal;


    private BigDecimal shippingFee;


    private BigDecimal totalValue;


    private OffsetDateTime creationDatetime;


    private OffsetDateTime confirmationDatetime;


    private OffsetDateTime cancellationDatetime;


    private OffsetDateTime deliveryDatetime;

    private PaymentMethodModel paymentMethod;
    private RestaurantOnlyNameModel restaurant;
    private UserModel customer;
    private AddressModel deliveryAddress;
    private OrderStatus status;
    private List<OrderItemModel> items;

}
