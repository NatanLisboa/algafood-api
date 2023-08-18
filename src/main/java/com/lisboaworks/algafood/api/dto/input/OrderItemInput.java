package com.lisboaworks.algafood.api.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderItemInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long productId;

    @ApiModelProperty(example = "2")
    @NotNull
    private Integer quantity;

    @ApiModelProperty(example = "Less spicy, please")
    private String note;

}
