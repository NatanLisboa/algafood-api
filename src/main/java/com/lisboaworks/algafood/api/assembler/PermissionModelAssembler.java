package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.PermissionController;
import com.lisboaworks.algafood.api.model.PermissionModel;
import com.lisboaworks.algafood.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PermissionModelAssembler extends RepresentationModelAssemblerSupport<Permission, PermissionModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PermissionModelAssembler() {
        super(PermissionController.class, PermissionModel.class);
    }

    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    public CollectionModel<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
        return super.toCollectionModel(permissions)
                .add(algaLinks.linkToPermissions());
    }

}
