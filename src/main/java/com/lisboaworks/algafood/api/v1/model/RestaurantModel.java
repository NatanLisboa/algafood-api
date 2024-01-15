package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Thai Gourmet")
	private String name;

	@Schema(example = "10.00")
	private BigDecimal shippingFee;

	private CuisineModel cuisine;

	@Schema(example = "true")
	private Boolean active;

	@Schema(example = "false")
	private Boolean open;

	private AddressModel address;
	
}
