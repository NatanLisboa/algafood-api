package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.model.PermissionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissions")
public interface PermissionControllerOpenApi {

    @ApiOperation("Get all registered permissions")
    CollectionModel<PermissionModel> findAll();

}
