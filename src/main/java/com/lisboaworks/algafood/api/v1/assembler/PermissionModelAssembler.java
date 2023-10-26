package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.PermissionController;
import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PermissionModelAssembler extends RepresentationModelAssemblerSupport<Permission, PermissionModel> {

    private final ModelMapper modelMapper;
    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @Autowired
    public PermissionModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
        super(PermissionController.class, PermissionModel.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
        this.securityHelper = securityHelper;
    }

    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    public CollectionModel<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
        CollectionModel<PermissionModel> permissionsCollectionModel = super.toCollectionModel(permissions);

        if (securityHelper.canGetUsersUserGroupsAndPermissions()) {
            permissionsCollectionModel.add(algaLinks.linkToPermissions());
        }

        return permissionsCollectionModel;
    }

}
