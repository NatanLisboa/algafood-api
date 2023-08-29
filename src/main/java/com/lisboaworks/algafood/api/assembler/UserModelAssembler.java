package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.UserModel;
import com.lisboaworks.algafood.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class UserModelAssembler {

    private final ModelMapper modelMapper;

    public UserModel toModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    public List<UserModel> toCollectionModel(Collection<User> users) {
        return users.stream()
                .map(this::toModel)
                .toList();
    }

}
