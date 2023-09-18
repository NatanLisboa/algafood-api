package com.lisboaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	private String name;

	@ApiModelProperty(example = "12.00")
	private BigDecimal shippingFee;

	private CuisineModel cuisine;
	private Boolean active;
	private Boolean open;
	private AddressModel address;
	
}
