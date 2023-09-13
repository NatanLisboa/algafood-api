package com.lisboaworks.algafood.api.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lisboaworks.algafood.api.model.UserGroupModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UserGroupsModel")
@Data
public class UserGroupsModelOpenApi {

    private UserGroupEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UserGroupsEmbeddedModel")
    @Data
    public static class UserGroupEmbeddedModelOpenApi {

       @JsonProperty("user-groups")
       private List<UserGroupModel> userGroups;

    }

}
