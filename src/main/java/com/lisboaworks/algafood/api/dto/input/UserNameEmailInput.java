package com.lisboaworks.algafood.api.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserNameEmailInput {

    @ApiModelProperty(example = "David Coleman")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "david.coleman2@gmail.com")
    @Email
    @NotBlank
    private String email;

}
