package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.PermissionModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class PermissionsModelOpenApi {

    private PermissionsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class PermissionsEmbeddedModelOpenApi {

        private List<PermissionModel> permissions;

    }

}
