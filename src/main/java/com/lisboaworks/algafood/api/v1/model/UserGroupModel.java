package com.lisboaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "user-groups")
@Getter
@Setter
public class UserGroupModel extends RepresentationModel<UserGroupModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Manager")
    private String name;

}
