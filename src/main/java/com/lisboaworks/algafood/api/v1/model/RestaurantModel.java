package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {


	private Long id;


	private String name;


	private BigDecimal shippingFee;

	private CuisineModel cuisine;
	private Boolean active;
	private Boolean open;
	private AddressModel address;
	
}
