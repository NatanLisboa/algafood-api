package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.StateModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("StatesModel")
@Data
public class StatesModelOpenApi {

    private StatesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("StatesEmbeddedModel")
    @Data
    public static class StatesEmbeddedModelOpenApi {

        private List<StateModel> states;

    }

}
