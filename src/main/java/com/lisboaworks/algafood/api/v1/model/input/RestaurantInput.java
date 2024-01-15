package com.lisboaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInput {

	@NotBlank
	@Schema(example = "Thai Fusion Restaurant")
	private String name;

	@NotNull
    @PositiveOrZero
	@Schema(example = "12.00")
	private BigDecimal shippingFee;
	
	@Valid
    @NotNull
	private CuisineIdInput cuisine;

	@Valid
	@NotNull
	private AddressInput address;

}
