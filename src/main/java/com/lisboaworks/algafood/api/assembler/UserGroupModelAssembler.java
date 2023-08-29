package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.UserGroupModel;
import com.lisboaworks.algafood.domain.model.UserGroup;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class UserGroupModelAssembler {

    private final ModelMapper modelMapper;

    public UserGroupModel toModel(UserGroup userGroup) {
        return modelMapper.map(userGroup, UserGroupModel.class);
    }

    public List<UserGroupModel> toCollectionModel(Collection<UserGroup> userGroups) {
        return userGroups.stream()
                .map(this::toModel)
                .toList();
    }

}
