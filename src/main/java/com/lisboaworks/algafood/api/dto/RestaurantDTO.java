package com.lisboaworks.algafood.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.dto.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantDTO {

	@JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
	private Long id;

	@JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
	private String name;

	@JsonView(RestaurantView.Summary.class)
	private BigDecimal shippingFee;

	@JsonView(RestaurantView.Summary.class)
	private CuisineDTO cuisine;
	private Boolean active;
	private Boolean open;
	private AddressDTO address;
	
}
