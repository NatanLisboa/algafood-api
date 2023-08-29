package com.lisboaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserChangePasswordInput {

    @ApiModelProperty(example = "123")
    @NotBlank
    private String currentPassword;

    @ApiModelProperty(example = "PotatoHead@123")
    @NotBlank
    private String newPassword;

}
