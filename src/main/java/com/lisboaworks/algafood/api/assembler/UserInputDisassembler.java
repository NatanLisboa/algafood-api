package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.input.UserIdEmailInput;
import com.lisboaworks.algafood.api.dto.input.UserInput;
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

    public void copyToDomainObject(UserIdEmailInput userInput, User user) {
        modelMapper.map(userInput, user);
    }


}
