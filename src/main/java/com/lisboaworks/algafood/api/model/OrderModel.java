package com.lisboaworks.algafood.api.model;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "317a132b-7506-4e5d-aa6f-e7fc6af21248")
    private String code;

    @ApiModelProperty(example = "100.00")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingFee;

    @ApiModelProperty(example = "110.00")
    private BigDecimal totalValue;

    @ApiModelProperty(example = "2023-07-08T11:00:00Z")
    private OffsetDateTime creationDatetime;

    @ApiModelProperty(example = "2023-07-09T11:00:00Z")
    private OffsetDateTime confirmationDatetime;

    @ApiModelProperty(example = "2023-07-08T16:00:00Z")
    private OffsetDateTime cancellationDatetime;

    @ApiModelProperty(example = "2023-07-11T11:00:00Z")
    private OffsetDateTime deliveryDatetime;

    private PaymentMethodModel paymentMethod;
    private RestaurantOnlyNameModel restaurant;
    private UserModel customer;
    private AddressModel deliveryAddress;
    private OrderStatus status;
    private List<OrderItemModel> items;

}
