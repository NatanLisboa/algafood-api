package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInput {

    @NotBlank
    @Schema(example = "João das Couves")
    private String name;

    @Email
    @NotBlank
    @Schema(example = "joaodascouves.sel@algafood.com")
    private String email;

    @NotBlank
    @Schema(example = "123")
    private String password;

}
