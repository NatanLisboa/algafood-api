package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.assembler.StateInputDisassembler;
import com.lisboaworks.algafood.api.v1.assembler.StateModelAssembler;
import com.lisboaworks.algafood.api.v1.model.StateModel;
import com.lisboaworks.algafood.api.v1.model.input.StateInput;
import com.lisboaworks.algafood.api.v1.openapi.controller.StateControllerOpenApi;
import com.lisboaworks.algafood.core.security.CheckSecurity;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import com.lisboaworks.algafood.domain.service.StateRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/states", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StateController implements StateControllerOpenApi {

    private final StateRepository stateRepository;
    private final StateRegisterService stateRegisterService;
    private final StateModelAssembler stateModelAssembler;
    private final StateInputDisassembler stateInputDisassembler;

    @GetMapping
    @CheckSecurity.States.CanGet
    public CollectionModel<StateModel> findAll() {
        return stateModelAssembler.toCollectionModel(stateRepository.findAll());
    }

    @GetMapping("/{stateId}")
    @CheckSecurity.States.CanGet
    public StateModel findById(@PathVariable Long stateId) {
    	State state = stateRegisterService.findOrThrowException(stateId);
        return stateModelAssembler.toModel(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.States.CanEdit
    public StateModel add(@RequestBody @Valid StateInput stateInput) {
    	State state = stateInputDisassembler.toDomainObject(stateInput);
        return stateModelAssembler.toModel(stateRegisterService.save(state));
    }

    @PutMapping("/{stateId}")
    @CheckSecurity.States.CanEdit
    public StateModel update(@PathVariable Long stateId,
                             @RequestBody @Valid StateInput newStateInput) {
        State state = stateRegisterService.findOrThrowException(stateId);
        stateInputDisassembler.copyToDomainObject(newStateInput, state);
        return stateModelAssembler.toModel(stateRegisterService.save(state));
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.States.CanEdit
    public void delete(@PathVariable Long stateId) {
        stateRegisterService.delete(stateId);
    }

}
