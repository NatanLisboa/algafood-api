package com.lisboaworks.algafood.api.v2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel("CuisineModel")
@Relation(collectionRelation = "cuisines")
@Getter
@Setter
public class CuisineModelV2 extends RepresentationModel<CuisineModelV2> {

	@ApiModelProperty(example = "1")
	private Long cuisineId;

	@ApiModelProperty(example = "Thai")
	private String cuisineName;

}
