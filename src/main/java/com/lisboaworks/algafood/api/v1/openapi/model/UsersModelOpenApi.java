package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.UserModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class UsersModelOpenApi {

    private UsersEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class UsersEmbeddedModelOpenApi {

        private List<UserModel> users;

    }

}
