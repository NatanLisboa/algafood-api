package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
@Getter
@Setter
public class StateModel extends RepresentationModel<StateModel> {

	@ApiModelProperty(example = "1")
	private Long id;


	@ApiModelProperty(example = "New York")
	private String name;
	
}
