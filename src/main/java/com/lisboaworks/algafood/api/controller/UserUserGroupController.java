package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserGroupModelAssembler;
import com.lisboaworks.algafood.api.model.UserGroupModel;
import com.lisboaworks.algafood.api.openapi.controller.UserUserGroupControllerOpenApi;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/{userId}/user-groups")
@AllArgsConstructor
public class UserUserGroupController implements UserUserGroupControllerOpenApi {

    private final UserGroupModelAssembler userGroupModelAssembler;
    private final UserRegisterService userRegisterService;

    @GetMapping
    public CollectionModel<UserGroupModel> findAll(@PathVariable Long userId) {
        User user = userRegisterService.findOrThrowException(userId);
        return userGroupModelAssembler.toCollectionModel(user.getUserGroups());
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
