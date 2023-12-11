package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import org.springframework.hateoas.CollectionModel;

public interface PermissionControllerOpenApi {

    CollectionModel<PermissionModel> findAll();

}
