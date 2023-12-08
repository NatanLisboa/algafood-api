package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cuisines")
@Getter
@Setter
public class CuisineModel extends RepresentationModel<CuisineModel> {


	private Long id;


	private String name;

}
