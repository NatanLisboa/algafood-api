package com.lisboaworks.algafood.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserIdEmailInput {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;

}