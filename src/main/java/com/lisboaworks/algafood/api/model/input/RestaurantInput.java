package com.lisboaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(example = "Thai Best Cuisine")
	@NotBlank
	private String name;

	@ApiModelProperty(example = "15.00")
	@NotNull
    @PositiveOrZero
	private BigDecimal shippingFee;
	
	@Valid
    @NotNull
	private CuisineIdInput cuisine;

	@Valid
	@NotNull
	private AddressInput address;

}
