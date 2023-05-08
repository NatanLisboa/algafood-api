package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegisterService cuisineRegisterService;

    @GetMapping
    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cuisine findById(@PathVariable Long id) {
        return cuisineRegisterService.findOrThrowException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine) {
        return cuisineRegisterService.save(cuisine);
    }

    @PutMapping("/{id}")
    public Cuisine update(@PathVariable Long id,
                                          @RequestBody Cuisine newCuisine) {
        Cuisine cuisine = cuisineRegisterService.findOrThrowException(id);
        BeanUtils.copyProperties(newCuisine, cuisine, "id");
        return cuisineRegisterService.save(cuisine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuisineRegisterService.delete(id);
    }

}
