package com.lisboaworks.algafood.api.v1.openapi.model;

import com.lisboaworks.algafood.api.v1.model.StateModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
public class StatesModelOpenApi {

    private StatesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class StatesEmbeddedModelOpenApi {

        private List<StateModel> states;

    }

}
