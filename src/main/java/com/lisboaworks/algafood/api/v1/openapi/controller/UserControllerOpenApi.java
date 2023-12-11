package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserModel;
import com.lisboaworks.algafood.api.v1.model.input.UserChangePasswordInput;
import com.lisboaworks.algafood.api.v1.model.input.UserInput;
import com.lisboaworks.algafood.api.v1.model.input.UserNameEmailInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface UserControllerOpenApi {

    CollectionModel<UserModel> findAll();

    UserModel findById(Long userId);

    UserModel add(UserInput userInput);

    UserModel update(Long userId, UserNameEmailInput newUserInput);

    void changePassword(Long userId, UserChangePasswordInput newPasswordInput);

}
