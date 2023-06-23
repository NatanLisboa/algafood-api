package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.PermissionDTO;
import com.lisboaworks.algafood.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class PermissionDTOAssembler {

    private final ModelMapper modelMapper;

    public PermissionDTO toDTO(Permission permission) {
        return modelMapper.map(permission, PermissionDTO.class);
    }

    public List<PermissionDTO> toDTOList(Collection<Permission> permissions) {
        return permissions.stream()
                .map(this::toDTO)
                .toList();
    }

}
