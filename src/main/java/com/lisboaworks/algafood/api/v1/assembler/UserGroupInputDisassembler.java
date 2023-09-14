package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.model.input.UserGroupInput;
import com.lisboaworks.algafood.domain.model.UserGroup;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserGroupInputDisassembler {
    
    private final ModelMapper modelMapper;

    public UserGroup toDomainObject(UserGroupInput userGroupInput) {
        return modelMapper.map(userGroupInput, UserGroup.class);
    }

    public void copyToDomainObject(UserGroupInput userGroupInput, UserGroup userGroup) {
        modelMapper.map(userGroupInput, userGroup);
    }


}
