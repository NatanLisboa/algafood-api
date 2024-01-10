package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "orderItems")
@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    @Schema(example = "1")
    private Long productId;

    @Schema(example = "Tom Yum Goong")
    private String productName;

    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "19.90")
    private BigDecimal unitPrice;

    @Schema(example = "39.80")
    private BigDecimal totalPrice;

    @Schema(example = "Less spicy, please")
    private String note;

}
