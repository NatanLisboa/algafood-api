package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class OrderItemInput {

    @NotNull
    @Schema(example = "1")
    private Long productId;

    @NotNull
    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "Less spicy, please")
    private String note;

}
