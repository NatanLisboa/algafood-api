package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.UserDTO;
import com.lisboaworks.algafood.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDTOAssembler {

    private final ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .toList();
    }

}
