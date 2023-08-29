package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.PermissionModel;
import com.lisboaworks.algafood.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class PermissionModelAssembler {

    private final ModelMapper modelMapper;

    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    public List<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream()
                .map(this::toModel)
                .toList();
    }

}
