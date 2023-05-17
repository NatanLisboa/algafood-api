package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import com.lisboaworks.algafood.domain.service.StateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegisterService;

    @GetMapping
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @GetMapping("/{id}")
    public State findById(@PathVariable Long id) {
        return stateRegisterService.findOrThrowException(id);
    }

    @PostMapping
    public State add(@RequestBody State state) {
        return stateRegisterService.save(state);
    }


    @PutMapping("/{id}")
    public State update(@PathVariable Long id,
                                    @RequestBody State newState) {
        State state = stateRegisterService.findOrThrowException(id);
        BeanUtils.copyProperties(newState, state, "id");
        return stateRegisterService.save(state);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateRegisterService.delete(id);
    }

}
