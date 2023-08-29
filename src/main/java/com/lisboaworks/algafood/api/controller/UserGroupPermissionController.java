package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.PermissionModelAssembler;
import com.lisboaworks.algafood.api.model.PermissionModel;
import com.lisboaworks.algafood.api.openapi.controller.UserGroupPermissionControllerOpenApi;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.service.UserGroupRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user-groups/{userGroupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserGroupPermissionController implements UserGroupPermissionControllerOpenApi {

    private final PermissionModelAssembler permissionModelAssembler;
    private final UserGroupRegisterService userGroupRegisterService;

    @GetMapping
    public List<PermissionModel> findAll(@PathVariable Long userGroupId) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        return permissionModelAssembler.toCollectionModel(userGroup.getPermissions());
    }
    
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userGroupId, @PathVariable Long permissionId) {
        userGroupRegisterService.associatePermission(userGroupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userGroupId, @PathVariable Long permissionId) {
        userGroupRegisterService.disassociatePermission(userGroupId, permissionId);
    }

}
