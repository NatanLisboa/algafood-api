package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.input.PermissionInput;
import com.lisboaworks.algafood.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PermissionInputDisassembler {
    
    private final ModelMapper modelMapper;

    public Permission toDomainObject(PermissionInput permissionInput) {
        return modelMapper.map(permissionInput, Permission.class);
    }

    public void copyToDomainObject(PermissionInput permissionInput, Permission permission) {
        modelMapper.map(permissionInput, permission);
    }


}
