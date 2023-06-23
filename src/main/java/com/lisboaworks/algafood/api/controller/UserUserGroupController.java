package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserGroupDTOAssembler;
import com.lisboaworks.algafood.api.dto.UserGroupDTO;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/user-groups")
@AllArgsConstructor
public class UserUserGroupController {

    private final UserGroupDTOAssembler userGroupDTOAssembler;
    private final UserRegisterService userRegisterService;

    @GetMapping
    public List<UserGroupDTO> findAll(@PathVariable Long userId) {
        User user = userRegisterService.findOrThrowException(userId);
        return userGroupDTOAssembler.toDTOList(user.getUserGroups());
    }
    
    @PutMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long userGroupId) {
        userRegisterService.associateUserGroup(userId, userGroupId);
    }

    @DeleteMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long userGroupId) {
        userRegisterService.disassociateUserGroup(userId, userGroupId);
    }

}
