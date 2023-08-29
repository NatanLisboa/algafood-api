package com.lisboaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdatePasswordInput {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

}
