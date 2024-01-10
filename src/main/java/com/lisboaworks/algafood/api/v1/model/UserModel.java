package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "users")
@Getter
@Setter
public class UserModel extends RepresentationModel<UserModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "João da Silva")
    private String name;

    @Schema(example = "joao.man@algafood.com")
    private String email;

}
