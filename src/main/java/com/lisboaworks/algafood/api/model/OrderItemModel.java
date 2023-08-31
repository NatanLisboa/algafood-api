package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "orderItems")
@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    @ApiModelProperty(example = "1")
    private Long productId;

    @ApiModelProperty(example = "Tom Yum Goong")
    private String productName;

    @ApiModelProperty(example = "2")
    private Integer quantity;

    @ApiModelProperty(example = "30.90")
    private BigDecimal unitPrice;

    @ApiModelProperty(example = "61.80")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "Less spicy, please")
    private String note;

}
