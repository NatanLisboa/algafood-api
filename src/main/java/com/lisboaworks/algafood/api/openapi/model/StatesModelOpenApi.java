package com.lisboaworks.algafood.api.openapi.model;

import com.lisboaworks.algafood.api.model.StateModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("StatesModel")
@Data
public class StatesModelOpenApi {

    private StateEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("StatesEmbeddedModel")
    @Data
    public static class StateEmbeddedModelOpenApi {

        private List<StateModel> states;

    }

}
