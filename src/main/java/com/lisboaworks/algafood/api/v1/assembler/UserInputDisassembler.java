package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.model.input.UserInput;
import com.lisboaworks.algafood.api.v1.model.input.UserNameEmailInput;
import com.lisboaworks.algafood.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserInputDisassembler {
    
    private final ModelMapper modelMapper;

    public User toDomainObject(UserInput userInput) {
        return modelMapper.map(userInput, User.class);
    }

    public void copyToDomainObject(UserNameEmailInput userInput, User user) {
        modelMapper.map(userInput, user);
    }


}
