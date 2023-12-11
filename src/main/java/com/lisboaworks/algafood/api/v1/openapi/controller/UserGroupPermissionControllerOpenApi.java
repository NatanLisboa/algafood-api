package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface UserGroupPermissionControllerOpenApi {

    CollectionModel<PermissionModel> findAll(Long userGroupId);

    ResponseEntity<Void> associate(Long userGroupId, Long permissionId);

    ResponseEntity<Void> disassociate(Long userGroupId, Long permissionId);

}
