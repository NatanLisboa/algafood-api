package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.assembler.UserInputDisassembler;
import com.lisboaworks.algafood.api.v1.assembler.UserModelAssembler;
import com.lisboaworks.algafood.api.v1.model.UserModel;
import com.lisboaworks.algafood.api.v1.model.input.UserChangePasswordInput;
import com.lisboaworks.algafood.api.v1.model.input.UserInput;
import com.lisboaworks.algafood.api.v1.model.input.UserNameEmailInput;
import com.lisboaworks.algafood.api.v1.openapi.controller.UserControllerOpenApi;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.repository.UserRepository;
import com.lisboaworks.algafood.domain.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController implements UserControllerOpenApi {

    private final UserRegisterService userRegisterService;
    private final UserModelAssembler userModelAssembler;
    private final UserRepository userRepository;
    private final UserInputDisassembler userInputDisassembler;

    @GetMapping
    public CollectionModel<UserModel> findAll() {
        return userModelAssembler.toCollectionModel(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public UserModel findById(@PathVariable Long userId) {
        User user = userRegisterService.findOrThrowException(userId);
        return userModelAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel add(@Valid @RequestBody UserInput userInput) {
        User user = userInputDisassembler.toDomainObject(userInput);
        return userModelAssembler.toModel(userRegisterService.save(user));
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId,
                            @RequestBody @Valid UserNameEmailInput newUserInput) {
        User user = userRegisterService.findOrThrowException(userId);
        userInputDisassembler.copyToDomainObject(newUserInput, user);
        return userModelAssembler.toModel(userRegisterService.save(user));
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long userId,
                          @RequestBody @Valid UserChangePasswordInput newPasswordInput) {
        userRegisterService.changePassword(userId, newPasswordInput.getCurrentPassword(), newPasswordInput.getNewPassword());
    }

}
