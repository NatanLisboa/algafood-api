package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.assembler.PermissionModelAssembler;
import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import com.lisboaworks.algafood.api.v1.openapi.controller.UserGroupPermissionControllerOpenApi;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.service.UserGroupRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/user-groups/{userGroupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserGroupPermissionController implements UserGroupPermissionControllerOpenApi {

    private final PermissionModelAssembler permissionModelAssembler;
    private final UserGroupRegisterService userGroupRegisterService;
    private final AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<PermissionModel> findAll(@PathVariable Long userGroupId) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        CollectionModel<PermissionModel> userGroupPermissionsModel = permissionModelAssembler.toCollectionModel(userGroup.getPermissions());
        userGroupPermissionsModel.add(algaLinks.linkToUserGroupPermissionAssociation(userGroupId, "associate"));

        userGroupPermissionsModel.getContent().forEach(permission -> permission.add(algaLinks
                .linkToUserGroupPermissionDisassociation(userGroupId, permission.getId(), "disassociate")));

        return userGroupPermissionsModel;
    }
    
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long userGroupId, @PathVariable Long permissionId) {
        userGroupRegisterService.associatePermission(userGroupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long userGroupId, @PathVariable Long permissionId) {
        userGroupRegisterService.disassociatePermission(userGroupId, permissionId);
        return ResponseEntity.noContent().build();
    }

}
