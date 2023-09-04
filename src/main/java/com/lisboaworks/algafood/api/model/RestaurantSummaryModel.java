package com.lisboaworks.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.model.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantSummaryModel extends RepresentationModel<RestaurantSummaryModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	private String name;

	@ApiModelProperty(example = "12.00")
	private BigDecimal shippingFee;

	private CuisineModel cuisine;

}
