package com.lisboaworks.algafood.api.v2.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.v1.model.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cuisines")
@Getter
@Setter
public class CuisineModelV2 extends RepresentationModel<CuisineModelV2> {

	@ApiModelProperty(example = "1")
	@JsonView(RestaurantView.Summary.class)
	private Long cuisineId;

	@ApiModelProperty(example = "Thai")
	@JsonView(RestaurantView.Summary.class)
	private String cuisineName;

}
