package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.PermissionModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissionsModel")
@Data
public class PermissionsModelOpenApi {

    private PermissionsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PermissionsEmbeddedModel")
    @Data
    public static class PermissionsEmbeddedModelOpenApi {

        private List<PermissionModel> permissions;

    }

}
