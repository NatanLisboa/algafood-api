package com.lisboaworks.algafood.api.dto.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantInput {
	
	@NotBlank
	private String name;
	
	@NotNull
    @PositiveOrZero
	private BigDecimal shippingFee;
	
	@Valid
    @NotNull
	private CuisineIdInput cuisine;

}
