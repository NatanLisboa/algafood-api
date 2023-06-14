package com.lisboaworks.algafood.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
	
	private Long id;
	private String name;
	private BigDecimal shippingFee;
	private CuisineDTO cuisine;
	private Boolean active;
	private AddressDTO address;
	
}
