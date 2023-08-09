package com.lisboaworks.algafood.client.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class RestaurantInput {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal shippingFee;

    @NotNull
    private CuisineIdInput cuisine;

    @NotNull
    private AddressInput address;

}
