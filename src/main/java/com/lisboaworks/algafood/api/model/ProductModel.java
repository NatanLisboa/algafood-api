package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModel {

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
