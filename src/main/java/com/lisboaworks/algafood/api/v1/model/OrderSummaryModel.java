package com.lisboaworks.algafood.api.v1.model;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "522be832-c93b-4164-a390-e62114e6177d")
    private String code;

    @Schema(example = "63.70")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal shippingFee;

    @Schema(example = "73.70")
    private BigDecimal totalValue;

    @Schema(example = "2023-07-08T13:00:00Z")
    private OffsetDateTime creationDatetime;

    private RestaurantOnlyNameModel restaurant;

    private UserModel customer;

    @Schema(example = "CREATED")
    private OrderStatus status;

}
