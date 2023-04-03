package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.model.CuisineXMLWrapper;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisineXMLWrapper findAllXml() {
        return new CuisineXMLWrapper(cuisineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> findById(@PathVariable Long id) {
        Cuisine cuisine = cuisineRepository.findById(id);
        return ResponseEntity.ok(cuisine);
    }

}
