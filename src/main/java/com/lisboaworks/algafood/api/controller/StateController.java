package com.lisboaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lisboaworks.algafood.api.assembler.StateDTOAssembler;
import com.lisboaworks.algafood.api.assembler.StateInputDisassembler;
import com.lisboaworks.algafood.api.dto.StateDTO;
import com.lisboaworks.algafood.api.dto.input.StateInput;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import com.lisboaworks.algafood.domain.service.StateRegisterService;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegisterService;
    
    @Autowired
    private StateDTOAssembler stateDTOAssembler;
    
    @Autowired
    private StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public List<StateDTO> findAll() {
        return stateDTOAssembler.toDTOList(stateRepository.findAll());
    }

    @GetMapping("/{id}")
    public StateDTO findById(@PathVariable Long id) {
    	State state = stateRegisterService.findOrThrowException(id);
        return stateDTOAssembler.toDTO(state);
    }

    @PostMapping
    public StateDTO add(@RequestBody @Valid StateInput stateInput) {
    	State state = stateInputDisassembler.toDomainObject(stateInput);
        return stateDTOAssembler.toDTO(stateRegisterService.save(state));
    }


    @PutMapping("/{id}")
    public StateDTO update(@PathVariable Long id,
                                    @RequestBody @Valid StateInput newStateInput) {
        State state = stateRegisterService.findOrThrowException(id);
        stateInputDisassembler.copyToDomainObject(newStateInput, state);
        return stateDTOAssembler.toDTO(stateRegisterService.save(state));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateRegisterService.delete(id);
    }

}
