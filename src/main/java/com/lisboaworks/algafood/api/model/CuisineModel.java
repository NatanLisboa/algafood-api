package com.lisboaworks.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.model.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cuisines")
@Getter
@Setter
public class CuisineModel extends RepresentationModel<CuisineModel> {

	@ApiModelProperty(example = "1")
	@JsonView(RestaurantView.Summary.class)
	private Long id;

	@ApiModelProperty(example = "Thai")
	@JsonView(RestaurantView.Summary.class)
	private String name;

}
