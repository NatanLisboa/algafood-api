package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.CityController;
import com.lisboaworks.algafood.api.controller.UserGroupController;
import com.lisboaworks.algafood.api.model.CityModel;
import com.lisboaworks.algafood.api.model.UserGroupModel;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.UserGroup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UserGroupModelAssembler extends RepresentationModelAssemblerSupport<UserGroup, UserGroupModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UserGroupModelAssembler() {
        super(UserGroupController.class, UserGroupModel.class);
    }

    public UserGroupModel toModel(UserGroup userGroup) {
        UserGroupModel userGroupModel = this.createModelWithId(userGroup.getId(), userGroup);

        modelMapper.map(userGroup, userGroupModel);

        userGroupModel.add(algaLinks.linkToUserGroups("user-groups"));

        userGroupModel.add(algaLinks.linkToUserGroupPermissions(userGroup.getId(), "permissions"));

        return userGroupModel;
    }

    public CollectionModel<UserGroupModel> toCollectionModel(Iterable<? extends UserGroup> userGroups) {
        return super.toCollectionModel(userGroups)
                .add(linkTo(UserGroupController.class).withSelfRel());
    }
}
