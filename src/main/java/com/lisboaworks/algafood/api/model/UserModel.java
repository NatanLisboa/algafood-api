package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "David Coleman")
    private String name;

    @ApiModelProperty(example = "david.coleman@gmail.com")
    private String email;

}
