package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.StateDTOAssembler;
import com.lisboaworks.algafood.api.assembler.StateInputDisassembler;
import com.lisboaworks.algafood.api.dto.StateDTO;
import com.lisboaworks.algafood.api.dto.input.StateInput;
import com.lisboaworks.algafood.api.openapi.controller.StateControllerOpenApi;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import com.lisboaworks.algafood.domain.service.StateRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/states")
@AllArgsConstructor
public class StateController implements StateControllerOpenApi {

    private final StateRepository stateRepository;
    private final StateRegisterService stateRegisterService;
    private final StateDTOAssembler stateDTOAssembler;
    private final StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public List<StateDTO> findAll() {
        return stateDTOAssembler.toDTOList(stateRepository.findAll());
    }

    @GetMapping("/{stateId}")
    public StateDTO findById(@PathVariable Long stateId) {
    	State state = stateRegisterService.findOrThrowException(stateId);
        return stateDTOAssembler.toDTO(state);
    }

    @PostMapping
    public StateDTO add(@RequestBody @Valid StateInput stateInput) {
    	State state = stateInputDisassembler.toDomainObject(stateInput);
        return stateDTOAssembler.toDTO(stateRegisterService.save(state));
    }


    @PutMapping("/{stateId}")
    public StateDTO update(@PathVariable Long stateId,
                                    @RequestBody @Valid StateInput newStateInput) {
        State state = stateRegisterService.findOrThrowException(stateId);
        stateInputDisassembler.copyToDomainObject(newStateInput, state);
        return stateDTOAssembler.toDTO(stateRegisterService.save(state));
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        stateRegisterService.delete(stateId);
    }

}
