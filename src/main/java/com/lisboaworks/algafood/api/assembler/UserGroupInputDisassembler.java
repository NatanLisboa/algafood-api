package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.input.UserGroupInput;
import com.lisboaworks.algafood.domain.model.UserGroup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGroupInputDisassembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserGroup toDomainObject(UserGroupInput userGroupInput) {
        return modelMapper.map(userGroupInput, UserGroup.class);
    }

    public void copyToDomainObject(UserGroupInput userGroupInput, UserGroup userGroup) {
        modelMapper.map(userGroupInput, userGroup);
    }


}
