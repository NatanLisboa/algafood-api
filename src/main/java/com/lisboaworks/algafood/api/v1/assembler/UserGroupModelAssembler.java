package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.UserGroupController;
import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.UserGroup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UserGroupModelAssembler extends RepresentationModelAssemblerSupport<UserGroup, UserGroupModel> {

    private final ModelMapper modelMapper;
    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @Autowired
    public UserGroupModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
        super(UserGroupController.class, UserGroupModel.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
        this.securityHelper = securityHelper;
    }

    public UserGroupModel toModel(UserGroup userGroup) {
        UserGroupModel userGroupModel = this.createModelWithId(userGroup.getId(), userGroup);

        modelMapper.map(userGroup, userGroupModel);

        if (securityHelper.canGetUsersUserGroupsAndPermissions()) {
            userGroupModel.add(algaLinks.linkToUserGroups("user-groups"));

            userGroupModel.add(algaLinks.linkToUserGroupPermissions(userGroup.getId(), "permissions"));
        }

        return userGroupModel;
    }

    public CollectionModel<UserGroupModel> toCollectionModel(Iterable<? extends UserGroup> userGroups) {
        CollectionModel<UserGroupModel> userGroupsCollectionModel = super.toCollectionModel(userGroups);

        if (securityHelper.canGetUsersUserGroupsAndPermissions()) {
            userGroupsCollectionModel.add(linkTo(UserGroupController.class).withSelfRel());
        }

        return userGroupsCollectionModel;
    }
}
