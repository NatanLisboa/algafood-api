package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import com.lisboaworks.algafood.api.v1.model.input.UserGroupInput;
import org.springframework.hateoas.CollectionModel;

public interface UserGroupControllerOpenApi {

    CollectionModel<UserGroupModel> findAll();

    UserGroupModel findById(Long userGroupId);

    UserGroupModel add(UserGroupInput userGroupInput);

    UserGroupModel update(Long userGroupId, UserGroupInput newUserGroupInput);

    void delete(Long userGroupId);

}
