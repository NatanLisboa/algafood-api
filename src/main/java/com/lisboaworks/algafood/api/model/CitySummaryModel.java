package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Getter
@Setter
public class CitySummaryModel extends RepresentationModel<CitySummaryModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "New York City")
	private String name;

	@ApiModelProperty(example = "New York")
	private String state;

}