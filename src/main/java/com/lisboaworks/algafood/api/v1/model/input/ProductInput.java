package com.lisboaworks.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {

    @ApiModelProperty(example = "Tom Kha Gai")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "Chicken in coconut soup")
    @NotBlank
    private String description;

    @ApiModelProperty(example = "23.90")
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private Boolean active;

}
