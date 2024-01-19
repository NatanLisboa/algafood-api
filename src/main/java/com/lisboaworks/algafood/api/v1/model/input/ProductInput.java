package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {

    @NotBlank
    @Schema(example = "Tom Yum Goong Premium")
    private String name;

    @NotBlank
    @Schema(example = "Tom Yum Goong with extra shrimp and pepper")
    private String description;

    @NotNull
    @PositiveOrZero
    @Schema(example = "25.90")
    private BigDecimal price;

    @NotNull
    @Schema(example = "true")
    private Boolean active;

}
