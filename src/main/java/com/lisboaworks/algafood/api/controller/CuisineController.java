package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping
    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

}
