package com.lisboaworks.algafood.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantDTO {
	
	private Long id;
	private String name;
	private BigDecimal shippingFee;
	private CuisineDTO cuisine;
	private Boolean active;
	private Boolean open;
	private AddressDTO address;
	
}
