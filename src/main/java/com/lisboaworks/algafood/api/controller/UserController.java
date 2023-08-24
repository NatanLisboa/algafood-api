package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserDTOAssembler;
import com.lisboaworks.algafood.api.assembler.UserInputDisassembler;
import com.lisboaworks.algafood.api.dto.UserDTO;
import com.lisboaworks.algafood.api.dto.input.UserChangePasswordInput;
import com.lisboaworks.algafood.api.dto.input.UserNameEmailInput;
import com.lisboaworks.algafood.api.dto.input.UserInput;
import com.lisboaworks.algafood.api.openapi.controller.UserControllerOpenApi;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.repository.UserRepository;
import com.lisboaworks.algafood.domain.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController implements UserControllerOpenApi {

    private final UserRegisterService userRegisterService;
    private final UserDTOAssembler userDTOAssembler;
    private final UserRepository userRepository;
    private final UserInputDisassembler userInputDisassembler;

    @GetMapping
    public List<UserDTO> findAll() {
        return userDTOAssembler.toDTOList(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public UserDTO findById(@PathVariable Long userId) {
        User user = userRegisterService.findOrThrowException(userId);
        return userDTOAssembler.toDTO(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO add(@Valid @RequestBody UserInput userInput) {
        User user = userInputDisassembler.toDomainObject(userInput);
        return userDTOAssembler.toDTO(userRegisterService.save(user));
    }

    @PutMapping("/{userId}")
    public UserDTO update(@PathVariable Long userId,
                             @RequestBody @Valid UserNameEmailInput newUserInput) {
        User user = userRegisterService.findOrThrowException(userId);
        userInputDisassembler.copyToDomainObject(newUserInput, user);
        return userDTOAssembler.toDTO(userRegisterService.save(user));
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long userId,
                          @RequestBody @Valid UserChangePasswordInput newPasswordInput) {
        userRegisterService.changePassword(userId, newPasswordInput.getCurrentPassword(), newPasswordInput.getNewPassword());
    }

}
