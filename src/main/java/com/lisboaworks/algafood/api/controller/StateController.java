package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.StateModelAssembler;
import com.lisboaworks.algafood.api.assembler.StateInputDisassembler;
import com.lisboaworks.algafood.api.model.StateModel;
import com.lisboaworks.algafood.api.model.input.StateInput;
import com.lisboaworks.algafood.api.openapi.controller.StateControllerOpenApi;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import com.lisboaworks.algafood.domain.service.StateRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/states")
@AllArgsConstructor
public class StateController implements StateControllerOpenApi {

    private final StateRepository stateRepository;
    private final StateRegisterService stateRegisterService;
    private final StateModelAssembler stateModelAssembler;
    private final StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public CollectionModel<StateModel> findAll() {
        return stateModelAssembler.toCollectionModel(stateRepository.findAll());
    }

    @GetMapping("/{stateId}")
    public StateModel findById(@PathVariable Long stateId) {
    	State state = stateRegisterService.findOrThrowException(stateId);
        return stateModelAssembler.toModel(state);
    }

    @PostMapping
    public StateModel add(@RequestBody @Valid StateInput stateInput) {
    	State state = stateInputDisassembler.toDomainObject(stateInput);
        return stateModelAssembler.toModel(stateRegisterService.save(state));
    }


    @PutMapping("/{stateId}")
    public StateModel update(@PathVariable Long stateId,
                             @RequestBody @Valid StateInput newStateInput) {
        State state = stateRegisterService.findOrThrowException(stateId);
        stateInputDisassembler.copyToDomainObject(newStateInput, state);
        return stateModelAssembler.toModel(stateRegisterService.save(state));
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        stateRegisterService.delete(stateId);
    }

}
