package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.StateModel;
import com.lisboaworks.algafood.api.v1.model.input.StateInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface StateControllerOpenApi {

    CollectionModel<StateModel> findAll();

    StateModel findById(Long stateId);

    StateModel add(StateInput stateInput);

    StateModel update(Long stateId, StateInput newStateInput);

    void delete(Long stateId);

}
