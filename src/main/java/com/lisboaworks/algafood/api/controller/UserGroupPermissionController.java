package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.PermissionDTOAssembler;
import com.lisboaworks.algafood.api.dto.PermissionDTO;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.service.UserGroupRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-groups/{userGroupId}/permissions")
@AllArgsConstructor
public class UserGroupPermissionController {

    private final PermissionDTOAssembler permissionDTOAssembler;
    private final UserGroupRegisterService userGroupRegisterService;

    @GetMapping
    public List<PermissionDTO> findAll(@PathVariable Long userGroupId) {
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);
        return permissionDTOAssembler.toDTOList(userGroup.getPermissions());
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
