package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "orderItems")
@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {


    private Long productId;


    private String productName;


    private Integer quantity;


    private BigDecimal unitPrice;


    private BigDecimal totalPrice;


    private String note;

}
