package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.controller.CityController;
import com.lisboaworks.algafood.api.controller.UserController;
import com.lisboaworks.algafood.api.controller.UserUserGroupController;
import com.lisboaworks.algafood.api.model.CityModel;
import com.lisboaworks.algafood.api.model.UserModel;
import com.lisboaworks.algafood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    public UserModel toModel(User user) {
        UserModel userModel = this.createModelWithId(user.getId(), user);

        modelMapper.map(user, userModel);

        userModel.add(linkTo(methodOn(UserController.class)
                .findAll()).withRel("users"));

        userModel.add(linkTo(methodOn(UserUserGroupController.class)
                .findAll(user.getId())).withRel("user-groups"));

        return userModel;
    }

    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> users) {
        return super.toCollectionModel(users)
                .add(linkTo(UserController.class).withSelfRel());
    }

}