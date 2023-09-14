package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.assembler.PermissionModelAssembler;
import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import com.lisboaworks.algafood.api.v1.openapi.controller.PermissionControllerOpenApi;
import com.lisboaworks.algafood.domain.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PermissionController implements PermissionControllerOpenApi {

    private final PermissionModelAssembler permissionModelAssembler;
    private final PermissionRepository permissionRepository;

    @GetMapping
    public CollectionModel<PermissionModel> findAll() {
        return permissionModelAssembler.toCollectionModel(permissionRepository.findAll());
    }

}
