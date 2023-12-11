package com.lisboaworks.algafood.api.v1.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lisboaworks.algafood.api.v1.model.UserGroupModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class UserGroupsModelOpenApi {

    private UserGroupsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class UserGroupsEmbeddedModelOpenApi {

       @JsonProperty("user-groups")
       private List<UserGroupModel> userGroups;

    }

}
