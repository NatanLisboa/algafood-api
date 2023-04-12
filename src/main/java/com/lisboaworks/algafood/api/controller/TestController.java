package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping("/cuisines")
    public List<Cuisine> getCuisinesByName(@RequestParam String name) {
        return cuisineRepository.findCuisinesByName(name);
    }

    @GetMapping("/cuisine")
    public Optional<Cuisine> getCuisineByName(@RequestParam String name) {
        return cuisineRepository.findByName(name);
    }
}
