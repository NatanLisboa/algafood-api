package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Getter
@Setter
public class CityModel extends RepresentationModel<CityModel> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "New York City")
	private String name;

	private StateModel state;

}
