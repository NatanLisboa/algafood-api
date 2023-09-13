package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.UserModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsersModel")
@Data
public class UsersModelOpenApi {

    private UsersEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UsersEmbeddedModel")
    @Data
    public static class UsersEmbeddedModelOpenApi {

        private List<UserModel> users;

    }

}
