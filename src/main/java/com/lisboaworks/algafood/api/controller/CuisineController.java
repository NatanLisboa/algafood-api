package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<Cuisine> findById(@PathVariable Long id) {
        Cuisine cuisine = cuisineRepository.findById(id);

        if (Objects.isNull(cuisine)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cuisine);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine) {
        return cuisineRegisterService.save(cuisine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> update(@PathVariable Long id,
                                          @RequestBody Cuisine newCuisine) {
        Cuisine cuisine = cuisineRepository.findById(id);

        if (Objects.isNull(cuisine)) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(newCuisine, cuisine, "id");

        return ResponseEntity.ok(cuisineRegisterService.save(cuisine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cuisine> delete(@PathVariable Long id) {
        try {
            cuisineRegisterService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityAlreadyInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
