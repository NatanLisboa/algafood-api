package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface UserUserGroupControllerOpenApi {

    CollectionModel<UserGroupModel> findAll(Long userId);

    ResponseEntity<Void> associate(Long userId, Long userGroupId);

    ResponseEntity<Void> disassociate(Long userId, Long userGroupId);

}
