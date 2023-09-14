package com.lisboaworks.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInput {

    @ApiModelProperty(example = "David Coleman")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "david.coleman@gmail.com")
    @Email
    @NotBlank
    private String email;

    @ApiModelProperty(example = "123")
    @NotBlank
    private String password;

}
