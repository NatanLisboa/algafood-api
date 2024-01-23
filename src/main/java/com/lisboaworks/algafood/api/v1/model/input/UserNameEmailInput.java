package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserNameEmailInput {

    @NotBlank
    @Schema(example = "João das Tesouras")
    private String name;

    @Email
    @NotBlank
    @Schema(example = "joaodastesouras.man@algafood.com")
    private String email;

}
