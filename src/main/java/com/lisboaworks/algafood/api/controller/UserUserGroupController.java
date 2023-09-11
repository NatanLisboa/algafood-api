package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.assembler.UserGroupModelAssembler;
import com.lisboaworks.algafood.api.model.UserGroupModel;
import com.lisboaworks.algafood.api.openapi.controller.UserUserGroupControllerOpenApi;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/{userId}/user-groups")
@AllArgsConstructor
public class UserUserGroupController implements UserUserGroupControllerOpenApi {

    private final UserGroupModelAssembler userGroupModelAssembler;
    private final UserRegisterService userRegisterService;
    private final AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<UserGroupModel> findAll(@PathVariable Long userId) {
        User user = userRegisterService.findOrThrowException(userId);
        CollectionModel<UserGroupModel> userGroupsModel = userGroupModelAssembler.toCollectionModel(user.getUserGroups());
        userGroupsModel.add(algaLinks.linkToAssociateUserGroupToUser(userId, "associate"));

        userGroupsModel.getContent().forEach(userGroupModel -> userGroupModel.add(algaLinks
                .linkToDisassociateUserGroupFromUser(userId, userGroupModel.getId(), "disassociate")));

        return userGroupsModel;
    }
    
    @PutMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long userGroupId) {
        userRegisterService.associateUserGroup(userId, userGroupId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long userGroupId) {
        userRegisterService.disassociateUserGroup(userId, userGroupId);

        return ResponseEntity.noContent().build();
    }

}
