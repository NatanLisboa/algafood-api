package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import com.lisboaworks.algafood.domain.service.StateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody State newState) {
        State state = stateRegisterService.findOrThrowException(id);
        BeanUtils.copyProperties(newState, state, "id");
        return ResponseEntity.ok(stateRegisterService.save(state));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateRegisterService.delete(id);
    }

}
