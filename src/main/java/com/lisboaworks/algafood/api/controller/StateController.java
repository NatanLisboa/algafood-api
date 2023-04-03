package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<State> findAll() {
        return stateRepository.findAll();
    }

}
