package com.lisboaworks.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.model.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

	@ApiModelProperty(example = "1")
	@JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	@JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
	private String name;

	@ApiModelProperty(example = "12.00")
	@JsonView(RestaurantView.Summary.class)
	private BigDecimal shippingFee;

	@JsonView(RestaurantView.Summary.class)
	private CuisineModel cuisine;
	private Boolean active;
	private Boolean open;
	private AddressModel address;
	
}
