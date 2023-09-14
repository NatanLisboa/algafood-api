package com.lisboaworks.algafood.api.v1.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UserGroupsModel")
@Data
public class UserGroupsModelOpenApi {

    private UserGroupsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UserGroupsEmbeddedModel")
    @Data
    public static class UserGroupsEmbeddedModelOpenApi {

       @JsonProperty("user-groups")
       private List<UserGroupModel> userGroups;

    }

}
