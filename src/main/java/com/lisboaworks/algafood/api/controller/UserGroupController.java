package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserGroupDTOAssembler;
import com.lisboaworks.algafood.api.assembler.UserGroupInputDisassembler;
import com.lisboaworks.algafood.api.dto.UserGroupDTO;
import com.lisboaworks.algafood.api.dto.input.UserGroupInput;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.repository.UserGroupRepository;
import com.lisboaworks.algafood.domain.service.UserGroupRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-groups")
public class UserGroupController {

    @Autowired
    private UserGroupRegisterService userGroupRegisterService;

    @Autowired
    private UserGroupDTOAssembler userGroupDTOAssembler;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserGroupInputDisassembler userGroupInputDisassembler;

    @GetMapping
    public List<UserGroupDTO> findAll() {
        return userGroupDTOAssembler.toDTOList(userGroupRepository.findAll());
    }

    @GetMapping("/{userGroupId}")
    public UserGroupDTO findById(@PathVariable Long userGroupId) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        return userGroupDTOAssembler.toDTO(userGroup);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroupDTO add(@Valid @RequestBody UserGroupInput userGroupInput) {
        UserGroup userGroup = userGroupInputDisassembler.toDomainObject(userGroupInput);
        return userGroupDTOAssembler.toDTO(userGroupRegisterService.save(userGroup));
    }

    @PutMapping("/{userGroupId}")
    public UserGroupDTO update(@PathVariable Long userGroupId,
                             @RequestBody @Valid UserGroupInput newUserGroupInput) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        userGroupInputDisassembler.copyToDomainObject(newUserGroupInput, userGroup);
        return userGroupDTOAssembler.toDTO(userGroupRegisterService.save(userGroup));
    }

    @DeleteMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userGroupId) {
        userGroupRegisterService.delete(userGroupId);
    }

}
