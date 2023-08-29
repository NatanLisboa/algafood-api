package com.lisboaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserGroupInput {

    @ApiModelProperty(example = "Manager")
    @NotBlank
    private String name;

}
