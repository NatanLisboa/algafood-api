package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionModel extends RepresentationModel<PermissionModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "GET_CUISINES")
    private String name;

    @ApiModelProperty(example = "Allow get cuisines")
    private String description;

}
