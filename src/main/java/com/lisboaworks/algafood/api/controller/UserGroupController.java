package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserGroupInputDisassembler;
import com.lisboaworks.algafood.api.assembler.UserGroupModelAssembler;
import com.lisboaworks.algafood.api.model.UserGroupModel;
import com.lisboaworks.algafood.api.model.input.UserGroupInput;
import com.lisboaworks.algafood.api.openapi.controller.UserGroupControllerOpenApi;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.repository.UserGroupRepository;
import com.lisboaworks.algafood.domain.service.UserGroupRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user-groups", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserGroupController implements UserGroupControllerOpenApi {

    private final UserGroupRegisterService userGroupRegisterService;
    private final UserGroupModelAssembler userGroupModelAssembler;
    private final UserGroupRepository userGroupRepository;
    private final UserGroupInputDisassembler userGroupInputDisassembler;

    @GetMapping
    public CollectionModel<UserGroupModel> findAll() {
        return userGroupModelAssembler.toCollectionModel(userGroupRepository.findAll());
    }

    @GetMapping("/{userGroupId}")
    public UserGroupModel findById(@PathVariable Long userGroupId) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        return userGroupModelAssembler.toModel(userGroup);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroupModel add(@Valid @RequestBody UserGroupInput userGroupInput) {
        UserGroup userGroup = userGroupInputDisassembler.toDomainObject(userGroupInput);
        return userGroupModelAssembler.toModel(userGroupRegisterService.save(userGroup));
    }

    @PutMapping("/{userGroupId}")
    public UserGroupModel update(@PathVariable Long userGroupId,
                                 @RequestBody @Valid UserGroupInput newUserGroupInput) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        userGroupInputDisassembler.copyToDomainObject(newUserGroupInput, userGroup);
        return userGroupModelAssembler.toModel(userGroupRegisterService.save(userGroup));
    }

    @DeleteMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userGroupId) {
        userGroupRegisterService.delete(userGroupId);
    }

}
