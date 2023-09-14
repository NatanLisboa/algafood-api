package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.UserController;
import com.lisboaworks.algafood.api.v1.model.UserModel;
import com.lisboaworks.algafood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    public UserModel toModel(User user) {
        UserModel userModel = this.createModelWithId(user.getId(), user);

        modelMapper.map(user, userModel);

        userModel.add(algaLinks.linkToUsers("users"));

        userModel.add(algaLinks.linkToUserGroupsFromUser(user.getId(), "user-groups"));

        return userModel;
    }

    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> users) {
        return super.toCollectionModel(users)
                .add(linkTo(UserController.class).withSelfRel());
    }

}
