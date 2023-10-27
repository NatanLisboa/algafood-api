package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.assembler.UserGroupModelAssembler;
import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import com.lisboaworks.algafood.api.v1.openapi.controller.UserUserGroupControllerOpenApi;
import com.lisboaworks.algafood.core.security.CheckSecurity;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users/{userId}/user-groups")
@AllArgsConstructor
public class UserUserGroupController implements UserUserGroupControllerOpenApi {

    private final UserGroupModelAssembler userGroupModelAssembler;
    private final UserRegisterService userRegisterService;
    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @GetMapping
    @CheckSecurity.UsersUserGroupsPermissions.CanGet
    public CollectionModel<UserGroupModel> findAll(@PathVariable Long userId) {
        User user = userRegisterService.findOrThrowException(userId);
        CollectionModel<UserGroupModel> userGroupsModel = userGroupModelAssembler
                .toCollectionModel(user.getUserGroups())
                .removeLinks();

        if (securityHelper.canEditUsersUserGroupsAndPermissions()) {
            userGroupsModel.add(algaLinks.linkToAssociateUserGroupToUser(userId, "associate"));

            userGroupsModel.getContent().forEach(userGroupModel -> userGroupModel.add(algaLinks
                    .linkToDisassociateUserGroupFromUser(userId, userGroupModel.getId(), "disassociate")));
        }

        return userGroupsModel;
    }
    
    @PutMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UsersUserGroupsPermissions.CanEdit
    public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long userGroupId) {
        userRegisterService.associateUserGroup(userId, userGroupId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UsersUserGroupsPermissions.CanEdit
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long userGroupId) {
        userRegisterService.disassociateUserGroup(userId, userGroupId);

        return ResponseEntity.noContent().build();
    }

}
