package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserChangePasswordInput {

    @NotBlank
    @Schema(example = "123")
    private String currentPassword;

    @NotBlank
    @Schema(example = "abc")
    private String newPassword;

}
