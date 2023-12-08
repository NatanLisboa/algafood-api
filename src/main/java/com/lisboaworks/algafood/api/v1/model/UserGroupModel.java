package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "user-groups")
@Getter
@Setter
public class UserGroupModel extends RepresentationModel<UserGroupModel> {


    private Long id;


    private String name;

}
