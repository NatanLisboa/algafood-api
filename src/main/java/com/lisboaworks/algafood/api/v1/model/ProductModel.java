package com.lisboaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class ProductModel extends RepresentationModel<ProductModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Tom Yum Goong")
    private String name;

    @ApiModelProperty(example = "Spicy shrimp soup")
    private String description;

    @ApiModelProperty(example = "19.90")
    private BigDecimal price;
    private Boolean active;

}
