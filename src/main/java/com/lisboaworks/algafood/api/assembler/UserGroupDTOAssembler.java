package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.UserGroupDTO;
import com.lisboaworks.algafood.domain.model.UserGroup;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserGroupDTOAssembler {

    private final ModelMapper modelMapper;

    public UserGroupDTO toDTO(UserGroup userGroup) {
        return modelMapper.map(userGroup, UserGroupDTO.class);
    }

    public List<UserGroupDTO> toDTOList(List<UserGroup> userGroups) {
        return userGroups.stream()
                .map(this::toDTO)
                .toList();
    }

}
