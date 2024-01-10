package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Getter
@Setter
public class CitySummaryModel extends RepresentationModel<CitySummaryModel> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Phuket Town")
	private String name;

	@Schema(example = "Phuket")
	private String state;

}
