package com.lisboaworks.algafood.api.v1.model;

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
	private Long id;

	@ApiModelProperty(example = "Thai")
	private String name;

}
